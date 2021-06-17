package com.quizme.Controller;

import com.quizme.Model.LoginObj;
import com.quizme.Model.User;
import com.quizme.Repository.UserRepo;
import com.quizme.Security.AuthenticationResponce;
import com.quizme.Security.JwtTokenUtil;
import com.quizme.Service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;


@RestController
public class UserController {

    @Autowired
    UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    MyUserDetailsService userDetailsService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    //testing purpose
    @GetMapping("/all/hello")
    @ResponseBody
    public String hello()
    {
        return "hello";
    }


    @PostMapping(value="/all/registration",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String register(@Valid @RequestBody User user)
    {
        User regUser = userRepo.findByuserName(user.getUserName());
        //check whether user is already exists
        if(regUser != null) {
            return "userName is already exist";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);

        //successfully registered and return the registered user
        return "successfully registered";
    }

    //for user login
    @PostMapping(value="/all/login",produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> login(@Valid @RequestBody LoginObj login) throws Exception {

        User user = userRepo.findByuserName(login.getUserName());

        if(user == null){
            return new ResponseEntity<>(
                    "user not found",
                    HttpStatus.BAD_REQUEST
            );
        }

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken( login.getUserName(),login.getPassword(),null)
            );
        }catch(BadCredentialsException e) {
            return new ResponseEntity<>(
                    "Incorrect userName or Password.",
                    HttpStatus.BAD_REQUEST
            );
        }

        final UserDetails userDetail=userDetailsService.loadUserByUsername(login.getUserName());
        final String jwtToken=jwtTokenUtil. generateToken(userDetail);

        return ResponseEntity.ok(new AuthenticationResponce(jwtToken));

    }

}
