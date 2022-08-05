package com.boyo.springsecurityproject2.dtos;

import lombok.Data;

@Data
public class RegistrationRequest {
    private String username;
    private String password;
}
