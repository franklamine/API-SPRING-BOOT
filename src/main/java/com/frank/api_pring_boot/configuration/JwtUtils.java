package com.frank.api_pring_boot.configuration;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {

    @Value("${app.secret-key}")
    private String secretKey;

    @Value("${app.expiration}")
    private long expirationTime;

   public String generateToken(String email) {
     Map<String, Object> claims = new HashMap<>();
     return createToken(claims, email);
   }


    private String createToken(Map<String, Object> claims, String subject) {
       return Jwts.builder()
               .setClaims(claims)
               .setSubject(subject)
               .setIssuedAt(new Date(System.currentTimeMillis()))
               .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
               .signWith(getSignKey(), SignatureAlgorithm.HS256)
               .compact();

    }

    private Key getSignKey() {
        return null;
    }
}
