package com.Userservice.service;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {
    // Secret key — same Gateway mein bhi use hogi
    private static final String SECRET =
            "mySecretKey12345678901234567890123456789012";

    // Token 24 hours valid rahega
    private static final long EXPIRATION =
            1000 * 60 * 60 * 24;

    // Key banana
    private Key getKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    // Token generate karo
    public String generateToken(String email, Long userId) {
        return Jwts.builder()
                .setSubject(email)
                .claim("userId", userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(
                        System.currentTimeMillis() + EXPIRATION))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Token valid hai?
    public boolean isTokenValid(String token) {
        try {
           Jwts.parser()
                    .setSigningKey(getKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long extractUserId(String token) {
        return Jwts.parser()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("userId", Long.class);  // ← Claims se nikalo
    }

    // Token se email nikalo
    public String extractEmail(String token) {
        return Jwts.parser()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

}
