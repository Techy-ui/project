package com.example.codecare.service;

import com.example.codecare.model.profile;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProfileService {
    private final List<profile> profiles = new ArrayList<>();

    public void save(profile profile) {
        profiles.add(profile);
    }

    public profile findByName(String name) {
        return profiles.stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public List<profile> getAllProfiles() {
        return profiles;
    }
}
