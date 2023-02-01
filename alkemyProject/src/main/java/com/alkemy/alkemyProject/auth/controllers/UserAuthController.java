/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alkemy.alkemyProject.auth.controllers;

import com.alkemy.alkemyProject.auth.DTO.AuthenticationRequest;
import com.alkemy.alkemyProject.auth.DTO.AuthenticationResponse;
import com.alkemy.alkemyProject.auth.DTO.UserDTO;
import com.alkemy.alkemyProject.auth.service.JwtUtils;
import com.alkemy.alkemyProject.auth.service.UserDetailsCustomService;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Fabi
 */

@RestController
@RequestMapping("/auth")
public class UserAuthController {



    private AuthenticationManager authenticationManager;
    private JwtUtils jwtUtils;
    private UserDetailsCustomService userDetailsService;

    @Autowired
    public UserAuthController(AuthenticationManager authenticationManager, JwtUtils jwtUtils, UserDetailsCustomService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }


   


    @PostMapping("/singup")
    public ResponseEntity<AuthenticationResponse> registrar(@Validated @RequestBody UserDTO user) throws Exception {

        this.userDetailsService.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



    @PostMapping("/singin")
    public ResponseEntity<AuthenticationResponse> login( @RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        UserDetails userDetails;
        try{
            Authentication auth=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),authenticationRequest.getPassword()));
        
        userDetails=(UserDetails)auth.getPrincipal();

    }catch(BadCredentialsException e){
    throw new Exception("nombre de usuario o contraseña incorrecta",e);

    }
    final String jwt=jwtUtils.generateToken(userDetails);
    return ResponseEntity.ok(new AuthenticationResponse(jwt));
}
}

