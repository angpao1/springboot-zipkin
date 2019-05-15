package com.example.microserviceregister.services;

import brave.ScopedSpan;
import brave.Tracer;
import com.example.microserviceregister.models.User;
import com.example.microserviceregister.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;
    private Tracer tracer;

    public UserService(UserRepository userRepository, Tracer tracer) {
        this.userRepository = userRepository;
        this.tracer = tracer;
    }

    public User addUser(User user) {
        ScopedSpan span = tracer.startScopedSpan("Add User to Database");
        User save = userRepository.save(user);
        span.finish();
        return save;
    }

    public Iterable<User> getAllUser() {
        ScopedSpan span = tracer.startScopedSpan("Query All Users from Database");
        Iterable<User> users = userRepository.findAll();
        span.finish();
        return users;
    }

    public User getUserOne(String id) {
        ScopedSpan span = tracer.startScopedSpan("Query User from Database");
        User userById = userRepository.findById(id).orElse(null);
        span.finish();
        return userById;
    }
}
