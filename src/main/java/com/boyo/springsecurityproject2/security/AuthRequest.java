package com.boyo.springsecurityproject2.security;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}
