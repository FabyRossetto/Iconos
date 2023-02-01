/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alkemy.alkemyProject.auth.service;

import com.alkemy.alkemyProject.auth.DTO.UserDTO;
import com.alkemy.alkemyProject.auth.Entity.UserEntity;
import com.alkemy.alkemyProject.auth.repository.UserRepository;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Fabi
 */

    @Service
public class UserDetailsCustomService implements UserDetailsService {

   
    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private EmailService emailservice;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity user = userRepository.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("User was not found.");
        }

        return new User(user.getUsername(), user.getPassword(), Collections.emptyList()); //esta collection esta vacia porque no implementamos roles

    }

   
    public boolean save (UserDTO userDTO){
        UserEntity user=new UserEntity();
        
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user = this.userRepository.save(user);
//        if(user!=null){
//            emailService.sendWelcomeEmailTo(user.getUsername());
//        }
        return user!=null;
    }

   
}

