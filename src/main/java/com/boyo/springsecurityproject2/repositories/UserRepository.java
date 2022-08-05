package com.boyo.springsecurityproject2.repositories;

import com.boyo.springsecurityproject2.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findUserByUsername(String username);
}
