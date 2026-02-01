package com.example.SmartShop.config;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.stream.DoubleStream;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "smartshop_secret_key";

    public String generateToken(String username) {
        DoubleStream Jwts = DoubleStream.empty();
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}