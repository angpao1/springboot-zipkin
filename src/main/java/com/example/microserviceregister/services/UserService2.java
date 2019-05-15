package com.example.microserviceregister.services;

import com.example.microserviceregister.models.User;
import com.example.microserviceregister.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService2 {

    private UserRepository userRepository;

    public UserService2(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }
}
