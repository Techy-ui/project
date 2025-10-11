package com.example.codecare.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@CrossOrigin(origins = "http://localhost:5500") // restrict to your frontend port in dev
@RestController
@RequestMapping("/api")
public class ProfileController {

    private final Map<String, Profile> profiles = new HashMap<>();

    @PostMapping("/Frontend/registration/register.html")
    public String register(@RequestBody Profile profile) {
        if (profile.getName() == null || profile.getName().isEmpty()) {
            return "Error: Name is required";
        }
        profiles.put(profile.getName().toLowerCase(), profile);
        return "Profile registered successfully for " + profile.getName() + " (Age: " + profile.getAge() + ")";
    }

    @PostMapping("/login")
    public String login(@RequestBody Profile profile) {
        Profile found = profiles.get(profile.getName().toLowerCase());
        if (found == null) {
            return "No profile found for " + profile.getName() + ". Please register.";
        }
        return "Welcome back, " + found.getName() + "! Your profile is active.";
    }

    @GetMapping("/profiles")
    public Collection<Profile> getAllProfiles() {
        return profiles.values();
    }
}

class Profile {
    private String name;
    private int age;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}
