package com.example.codecare.controller;

import com.example.codecare.model.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api")  // Prefix for API routes only
@CrossOrigin(origins = "https://techy-ui.github.io")
public class RegisterController {

    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

    private List<User> users = new ArrayList<>();

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        logger.info("Register request received for email: {}", user.getEmail());
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            logger.warn("Invalid email in register request");
            return ResponseEntity.badRequest().body("{\"error\": \"Email is required\"}");
        }
        if (users.stream().anyMatch(u -> u.getEmail().equals(user.getEmail()))) {
            logger.warn("Duplicate email attempt: {}", user.getEmail());
            return ResponseEntity.badRequest().body("{\"error\": \"Email already registered\"}");
        }
        users.add(user);
        logger.info("User registered successfully: {}", user.getEmail());
        return ResponseEntity.ok("{\"message\": \"User registered successfully\"}");
    }

    @GetMapping("/profiles")
    public List<User> getUsers() {
        logger.info("Fetching all profiles");
        return users;
    }
}