package com.example.codecare.controller;

import com.example.codecare.model.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "https://techy-ui.github.io")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    // Inject the same users list from RegisterController
    private final List<User> users;

    // Constructor-based injection
    public LoginController(RegisterController registerController) {
        this.users = registerController.getUsers();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginUser) {
        logger.info("Login attempt for email: {}", loginUser.getEmail());

        User user = users.stream()
                .filter(u -> u.getEmail().equals(loginUser.getEmail())
                          && u.getPassword().equals(loginUser.getPassword()))
                .findFirst()
                .orElse(null);

        if (user == null) {
            logger.warn("Invalid login attempt for email: {}", loginUser.getEmail());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("{\"error\":\"Invalid email or password\"}");
        }

        logger.info("Login successful for email: {}", loginUser.getEmail());
        return ResponseEntity.ok(user);
    }
}
