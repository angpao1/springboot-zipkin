package com.example.microserviceregister.services;

import brave.ScopedSpan;
import brave.Tracer;
import com.example.microserviceregister.models.User;
import com.example.microserviceregister.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;
    private UserService2 userService2;
    private Tracer tracer;

    public UserService(UserRepository userRepository, UserService2 userService2, Tracer tracer) {
        this.userRepository = userRepository;
        this.userService2 = userService2;
        this.tracer = tracer;
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public Iterable<User> getAllUser() {
        Iterable<User> users = userRepository.findAll();
        return users;
    }

    public User getUserOne(String id) {
        ScopedSpan span = tracer.startScopedSpan("Query User from Database");
        User userById = userService2.getUserById(id);
        span.finish();
        return userById;
    }
}
