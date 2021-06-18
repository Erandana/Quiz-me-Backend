package com.quizme.Service;


import java.util.Collection;

import com.quizme.Model.User;
import com.quizme.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;




@Service
public class MyUserDetailsService implements UserDetailsService{

    @Autowired
    UserRepo userRepository;

    @Override
    public MyUserDetailsPrinciple loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByuserName(userName);
        return new MyUserDetailsPrinciple(user);
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return null;
    }





}


