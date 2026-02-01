package com.example.SmartShop.controller;

import com.example.SmartShop.config.JwtUtil;
import com.example.SmartShop.dto.LoginRequest;
import com.example.SmartShop.dto.RegisterRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        return jwtUtil.generateToken(request.getUsername());
    }
    @PostMapping
    public String register(@RequestBody RegisterRequest request){
        return "User Registered Successfully";
    }
}
