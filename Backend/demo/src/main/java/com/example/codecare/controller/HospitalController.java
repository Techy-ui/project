package com.example.codecare.controller;

import com.example.codecare.model.Hospital;
import com.example.codecare.repository.HospitalRepository;
import com.example.codecare.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@CrossOrigin(origins = "https://techy-ui.github.io")
@RestController
@RequestMapping("/api/hospital")
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private HospitalRepository hospitalRepo;

    @PostMapping("/register")
    public ResponseEntity<?> registerHospital(@RequestBody Hospital hospital) {
        if (hospitalRepo.findByEmail(hospital.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already exists");
        }
        Hospital saved = hospitalService.registerHospital(hospital);
        return ResponseEntity.ok("Hospital registered successfully with ID: " + saved.getHospitalId());
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginHospital(@RequestBody Hospital hospital) {
        var found = hospitalRepo.findByHospitalId(hospital.getHospitalId());
        if (found.isEmpty()) return ResponseEntity.status(401).body("Invalid hospital ID");

        Hospital existing = found.get();
        if (!new BCryptPasswordEncoder().matches(hospital.getPassword(), existing.getPassword()))
            return ResponseEntity.status(401).body("Invalid password");

        return ResponseEntity.ok("Login successful");
    }
}
