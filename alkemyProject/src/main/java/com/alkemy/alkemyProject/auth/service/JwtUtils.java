/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alkemy.alkemyProject.auth.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 *
 * @author Fabi
 */
 @Service
public class JwtUtils {
 
    private final String SECRET_KEY = "secret";

    public String extractUsername (String token) { return extractClaim (token, Claims::getSubject);}

    public Date extractExpiration (String token) { return extractClaim(token, Claims::getExpiration);}

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) { return extractExpiration(token).before(new Date());}

    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        
        return createToken(claims, userDetails.getUsername());
    }

    public String createToken (Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date (System.currentTimeMillis() + 1000 * 60 * 60 * 10))// le da una validez al token de 10 hs, esto lo pódemos modificar por el tiempo que queramos que dure
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public Boolean validateToken (String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}

