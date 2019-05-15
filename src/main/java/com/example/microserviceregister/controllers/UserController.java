package com.example.microserviceregister.controllers;

import brave.ScopedSpan;
import brave.Tracer;
import com.example.microserviceregister.models.User;
import com.example.microserviceregister.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private UserService userService;
    private Tracer tracer;
    public UserController(UserService userService, Tracer tracer) {
        this.userService = userService;
        this.tracer = tracer;
    }

    @GetMapping("/users")
    public Iterable<User> getAllUsers() {
        return userService.getAllUser();
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

//    @GetMapping("/login/{id}")
//    public String getUserById(@PathVariable String id) {
//        RestTemplate restTemplate = new RestTemplate();
//        String user= restTemplate.getForEntity("http://localhost:8081/api/v2/users/" + id, String.class).getBody();
//        return user;
//    }
    @GetMapping("/login/{id}")
    public User getUserById(@PathVariable String id) {
        ScopedSpan span = tracer.startScopedSpan("Call Service");
        User user = userService.getUserOne(id);
        span.finish();
        return user;
    }
}
