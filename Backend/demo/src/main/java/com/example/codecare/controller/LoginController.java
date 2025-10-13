package com.example.codecare.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.codecare.model.User;
import com.example.codecare.repository.UserRepository;

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

    // ✅ Return user info for frontend
    // You can choose what fields to return; never return the password!
    return ResponseEntity.ok(new LoginResponse("Login successful", user.getEmail(), user.getName()));
}

// Inner static class for structured response
static class LoginResponse {
    private String message;
    private String email;
    private String name;

    public LoginResponse(String message, String email, String name) {
        this.message = message;
        this.email = email;
        this.name = name;
    }

    public String getMessage() { return message; }
    public String getEmail() { return email; }
    public String getName() { return name; }
}
}