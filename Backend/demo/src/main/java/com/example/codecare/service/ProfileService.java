package com.example.codecare.service;

import com.example.codecare.model.Profile;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {
    private final List<Profile> profiles = new ArrayList<>();

    public String save(Profile profile) {
        // Basic validation
        if (profile.getName() == null || profile.getName().isEmpty() || profile.getEmail() == null || profile.getEmail().isEmpty()) {
            return "Error: Name and Email are required";
        }
        // Check for duplicate email
        if (profiles.stream().anyMatch(p -> p.getEmail().equalsIgnoreCase(profile.getEmail()))) {
            return "Error: Email already registered";
        }
        profiles.add(profile);
        return "Profile registered successfully for " + profile.getName();
    }

    public Optional<Profile> findByEmail(String email) {
        return profiles.stream()
                .filter(p -> p.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    public List<Profile> getAllProfiles() {
        return new ArrayList<>(profiles);  // Return copy to prevent external modification
    }
}
