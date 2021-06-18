package com.quizme.Security;


import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.quizme.Service.MyUserDetailsPrinciple;
import com.quizme.Service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    MyUserDetailsService userDetails;

    @Autowired
    JwtTokenUtil JwttokenUtil;

    MyUserDetailsPrinciple myUserDetailsPrinciple;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        //exclude path pattern "/all/"
        String path = request.getRequestURI();
        if (path.substring(0,5).equals("/all/")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String authorizationHeader=request.getHeader("Authorization");
        System.out.println("Filtering...");

        String userName=null;
        String jwt=null;

        if(authorizationHeader==null) {
            throw new AccessDeniedException("Access Denied: Failed");

        }



        if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer ") ) {
            jwt= authorizationHeader.substring(7);
            userName=JwttokenUtil.getUsernameFromToken(jwt);


        }

        if (userName!= null && SecurityContextHolder.getContext().getAuthentication() == null) {
             myUserDetailsPrinciple = userDetails.loadUserByUsername(userName);

        }


        if (JwttokenUtil.validateToken(jwt, userDetails.loadUserByUsername(userName))) {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(myUserDetailsPrinciple, null, userDetails.getAuthorities());

            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        }

        filterChain.doFilter(request, response);
    }


}


