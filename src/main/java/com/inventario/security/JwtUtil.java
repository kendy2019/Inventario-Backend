package com.inventario.security;


import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

@Component
public class JwtUtil {

 private final String SECRET = "485748858454846555488214846646464"; // Cambiar	
 private final long EXPIRATION = 1000 * 60 * 60; // 1 hora 

 public String generarToken(String username) {
     return Jwts.builder()
             .setSubject(username)
             .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
             .signWith(SignatureAlgorithm.HS512, SECRET)
             .compact();
 }

 public String extraerUsername(String token) {
     return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody().getSubject();
 }

 public boolean validarToken(String token) {
     try {
         Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
         return true;
     } catch (JwtException e) {
         return false;
     }
 }
}
