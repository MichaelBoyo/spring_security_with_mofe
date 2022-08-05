package com.boyo.springsecurityproject2.service;

import com.boyo.springsecurityproject2.dtos.RegistrationRequest;
import com.boyo.springsecurityproject2.models.User;

public interface UserService {
    User findUserByUsername(String username);
    void register(RegistrationRequest request);
}
