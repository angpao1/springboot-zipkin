package com.example.microserviceregister.controllers;

import brave.ScopedSpan;
import brave.Tracer;
import com.example.microserviceregister.models.User;
import com.example.microserviceregister.services.UserService;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value="Fetches all Users in the database", response=User.class)
    @GetMapping("/users")
    public Iterable<User> getAllUsers() {
        ScopedSpan span = tracer.startScopedSpan("Call Service");
        Iterable<User> allUser = userService.getAllUser();
        span.finish();
        return allUser;
    }

    @ApiOperation(value="Add User to the database")
    @PostMapping("/users")
    public User addUser(@RequestBody User user) {
        ScopedSpan span = tracer.startScopedSpan("Call Service");
        User user1 = userService.addUser(user);
        span.finish();
        return user1;
    }

//    @GetMapping("/login/{id}")
//    public String getUserById(@PathVariable String id) {
//        RestTemplate restTemplate = new RestTemplate();
//        String user= restTemplate.getForEntity("http://localhost:8081/api/v2/users/" + id, String.class).getBody();
//        return user;
//    }
    @ApiOperation(value="Fetches id User in the database", response=User.class)
    @GetMapping("/login/{id}")
    public User getUserById(@PathVariable String id) {
        ScopedSpan span = tracer.startScopedSpan("Call Service");
        User user = userService.getUserOne(id);
        span.finish();
        return user;
    }
}
