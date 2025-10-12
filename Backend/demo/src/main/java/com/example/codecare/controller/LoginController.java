package com.example.codecare.controller;

import com.example.codecare.model.User;
import com.example.codecare.repository.UserRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "https://techy-ui.github.io")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginUser) {
        logger.info("Login attempt for email: {}", loginUser.getEmail());

        Optional<User> userOpt = userRepository.findByEmail(loginUser.getEmail());

        if (userOpt.isEmpty()) {
            logger.warn("Login failed - email not registered: {}", loginUser.getEmail());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("{\"error\":\"Invalid email or password\"}");
        }

        User user = userOpt.get();
        if (!passwordEncoder.matches(loginUser.getPassword(), user.getPassword())) {
            logger.warn("Login failed - incorrect password: {}", loginUser.getEmail());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("{\"error\":\"Invalid email or password\"}");
        }

        logger.info("Login successful for email: {}", loginUser.getEmail());
        return ResponseEntity.ok("{\"message\":\"Login successful\"}");
    }
}
