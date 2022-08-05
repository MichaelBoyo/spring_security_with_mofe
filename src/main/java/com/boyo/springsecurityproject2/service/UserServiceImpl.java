package com.boyo.springsecurityproject2.service;

import com.boyo.springsecurityproject2.dtos.RegistrationRequest;
import com.boyo.springsecurityproject2.models.Role;
import com.boyo.springsecurityproject2.models.User;
import com.boyo.springsecurityproject2.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User findUserByUsername(String username) {
        return null;
    }

    @Override
    public void register(RegistrationRequest request) {
        var newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.addRole(Role.USER);
        userRepository.save(newUser);
    }



}
