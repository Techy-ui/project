package com.example.codecare.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.slf4j.Logger;    
import org.slf4j.LoggerFactory;

@RestController  // No @RequestMapping—handles root directly
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @GetMapping("/")
    public ResponseEntity<String> home() {
        logger.info("Root endpoint accessed from HomeController");
        return ResponseEntity.ok("CodeCare API is running! Use /api/register for registration.");
    }

    // Optional: Health check (Railway likes this for monitoring)
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        logger.info("Health check accessed");
        return ResponseEntity.ok("{\"status\": \"UP\"}");
    }
}