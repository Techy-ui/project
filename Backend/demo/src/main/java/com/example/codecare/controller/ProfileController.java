package com.example.codecare.controller;

import com.example.codecare.model.Profile;
import com.example.codecare.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:5500", "http://localhost:5501"})  // Allow your Live Server ports
@RestController
@RequestMapping("/api")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping("/register")  // Matches frontend fetch to /api/register
    public String register(@RequestBody Profile profile) {
        return profileService.save(profile);
    }

    @PostMapping("/login")  // Matches frontend (update your login.html to POST here)
    public String login(@RequestBody Profile loginRequest) {  // Expect {email, password}
        Optional<Profile> found = profileService.findByEmail(loginRequest.getEmail());
        if (found.isEmpty()) {
            return "Error: No profile found for email " + loginRequest.getEmail() + ". Please register.";
        }
        Profile profile = found.get();
        if (!profile.getPassword().equals(loginRequest.getPassword())) {  // Simple check; use BCrypt in prod
            return "Error: Invalid password";
        }
        return "Welcome back, " + profile.getName() + "! Your profile is active. (Age: " + profile.getAge() + ")";
    }

    @GetMapping("/profiles")  // For admin/retrieval (e.g., dashboard)
    public Collection<Profile> getAllProfiles() {
        return profileService.getAllProfiles();
    }

    // Optional: Get single profile by email (for dashboard)
    @GetMapping("/profile/{email}")
    public Optional<Profile> getProfileByEmail(@PathVariable String email) {
        return profileService.findByEmail(email);
    }
}
