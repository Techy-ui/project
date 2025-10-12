package com.example.codecare.controller;

import com.example.codecare.model.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory; // For logging

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "https://techy-ui.github.io") // Fixed: No trailing slash, exact origin
public class RegisterController {

    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class); // Logging setup

    private List<User> users = new ArrayList<>();  // Temporary in-memory storage

    // Fix 404 on root: Simple GET for / (or /api/ if you prefer)
    @GetMapping("/")
    public ResponseEntity<String> home() {
        logger.info("Root endpoint accessed"); // Log it
        return ResponseEntity.ok("CodeCare API is running! Use /api/register for registration.");
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        logger.info("Register request received for email: {}", user.getEmail()); // Debug log

        // Basic validation and duplicate check
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            logger.warn("Invalid email in register request");
            return ResponseEntity.badRequest().body("{\"error\": \"Email is required\"}");
        }
        if (users.stream().anyMatch(u -> u.getEmail().equals(user.getEmail()))) {
            logger.warn("Duplicate email attempt: {}", user.getEmail());
            return ResponseEntity.badRequest().body("{\"error\": \"Email already registered\"}");
        }

        users.add(user);  // Store user
        logger.info("User registered successfully: {}", user.getEmail());
        return ResponseEntity.ok("{\"message\": \"User registered successfully\"}"); // JSON for frontend
    }

    @GetMapping("/profiles")
    public List<User> getUsers() {
        logger.info("Fetching all profiles"); // Log it
        return users;
    }
}