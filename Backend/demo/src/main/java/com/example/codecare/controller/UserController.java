package com.example.codecare.controller;

import com.example.codecare.model.User;
import com.example.codecare.repository.UserRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import java.nio.file.Path;
import org.springframework.core.io.Resource;


import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "https://techy-ui.github.io") // Allow your frontend
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Get user details by email (used for dashboard)
     */
    @GetMapping("/user/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/user/{email}/upload-photo")
    public ResponseEntity<?> uploadProfilePhoto(
            @PathVariable String email,
            @RequestParam("file") MultipartFile file) {

        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) return ResponseEntity.notFound().build();

        try {
            User user = userOpt.get();

            // Save file to filesystem
            String folder = "uploads/profile_photos/";
            String filename = email + "_" + file.getOriginalFilename();
            Path path = Paths.get(folder + filename);
            Files.createDirectories(path.getParent()); // create folder if not exists
            Files.write(path, file.getBytes());

            // Save path in database
            user.setPhotoPath(path.toString());
            userRepository.save(user);

            return ResponseEntity.ok("Photo uploaded successfully");

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to upload photo: " + e.getMessage());
        }
    }

    @GetMapping("/user/{email}/photo")
    public ResponseEntity<Resource> getUserPhoto(@PathVariable String email) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty() || userOpt.get().getPhotoPath() == null) {
            return ResponseEntity.notFound().build();
        }

        try {
            Path path = Paths.get(userOpt.get().getPhotoPath());
            Resource resource = new UrlResource(path.toUri());
            String contentType = Files.probeContentType(path);

            return ResponseEntity.ok()
                    .header("Content-Type", contentType != null ? contentType : "application/octet-stream")
                    .body(resource);

        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
    @PutMapping("/user/{email}")
    public ResponseEntity<?> updateUserProfile(
            @PathVariable String email,
            @RequestBody User updatedUser) {
    
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) return ResponseEntity.notFound().build();
    
        User user = userOpt.get();
    
        // Update editable fields
        user.setName(updatedUser.getName());
        user.setAge(updatedUser.getAge());
        user.setBloodGroup(updatedUser.getBloodGroup());
        user.setPhone(updatedUser.getPhone());
        user.setEmergencyContact(updatedUser.getEmergencyContact());
        user.setAddress(updatedUser.getAddress());
    
        userRepository.save(user);
        return ResponseEntity.ok("Profile updated successfully");
    }




}
