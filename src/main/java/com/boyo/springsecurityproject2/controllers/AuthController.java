package com.boyo.springsecurityproject2.controllers;

import com.boyo.springsecurityproject2.dtos.RegistrationRequest;
import com.boyo.springsecurityproject2.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistrationRequest request){
        userService.register(request);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }
}
