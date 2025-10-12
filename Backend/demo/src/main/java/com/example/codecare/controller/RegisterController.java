package com.example.codecare.controller;

import com.example.codecare.model.User;
import com.example.codecare.repository.UserRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "https://techy-ui.github.io")
public class RegisterController {

    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public RegisterController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        logger.info("Register request received for email: {}", user.getEmail());

        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            logger.warn("Invalid email in register request");
            return ResponseEntity.badRequest().body("{\"error\":\"Email is required\"}");
        }

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            logger.warn("Duplicate email attempt: {}", user.getEmail());
            return ResponseEntity.badRequest().body("{\"error\":\"Email already registered\"}");
        }

        // Hash the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        logger.info("User registered successfully: {}", user.getEmail());
        return ResponseEntity.ok("{\"message\":\"User registered successfully\"}");
    }
}