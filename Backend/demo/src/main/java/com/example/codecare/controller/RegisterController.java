package com.example.codecare.controller;

import com.example.codecare.model.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")  // Enable CORS
public class RegisterController {

    private List<User> users = new ArrayList<>();  // Temporary in-memory storage

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        users.add(user);  // Store user
        return ResponseEntity.ok("User registered successfully");
    }

    @GetMapping("/profiles")
    public List<User> getUsers() {
        return users;
    }
}
