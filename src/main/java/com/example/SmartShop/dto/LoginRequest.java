package com.example.SmartShop.dto;

import lombok.Data;
import org.jspecify.annotations.Nullable;

@Data
public class LoginRequest {
    private String email;
    private String password;

}
