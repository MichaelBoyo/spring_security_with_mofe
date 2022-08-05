package com.boyo.springsecurityproject2.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {

    @GetMapping("/protected")
    ResponseEntity<?> protectedMethod(){
        return new ResponseEntity<>("Access granted", HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/admin/restricted")
    ResponseEntity<?> admin(){
        return new ResponseEntity<>("Access granted to admin", HttpStatus.OK);
    }
}
