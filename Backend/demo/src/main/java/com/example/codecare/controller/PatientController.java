package com.example.codecare.controller;

import com.example.codecare.model.Patient;
import com.example.codecare.repository.PatientRepository;
import com.example.codecare.service.PatientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "https://techy-ui.github.io")
@RestController
@RequestMapping("/api/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientRepository patientRepo;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // -------------------------------
    // 1️⃣ Register Patient
    // -------------------------------
    @PostMapping("/register")
    public ResponseEntity<?> registerPatient(@RequestBody Patient patient) {
        if (patientRepo.findByEmail(patient.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Email already exists"));
        }

        // Encrypt password
        patient.setPassword(passwordEncoder.encode(patient.getPassword()));

        // Generate unique patient ID (you can customize this logic)
        patient.setPatientId("PAT" + System.currentTimeMillis());

        Patient saved = patientRepo.save(patient);
        return ResponseEntity.ok(Map.of(
                "message", "Patient registered successfully",
                "patientId", saved.getPatientId()
        ));
    }

    // -------------------------------
    // 2️⃣ Login Patient
    // -------------------------------
    @PostMapping("/login")
    public ResponseEntity<?> loginPatient(@RequestBody Patient loginReq) {
        Optional<Patient> foundOpt = patientRepo.findByEmail(loginReq.getEmail());
        if (foundOpt.isEmpty()) {
            return ResponseEntity.status(401).body(Map.of("error", "Invalid email"));
        }

        Patient existing = foundOpt.get();
        if (!passwordEncoder.matches(loginReq.getPassword(), existing.getPassword())) {
            return ResponseEntity.status(401).body(Map.of("error", "Invalid password"));
        }

        return ResponseEntity.ok(Map.of(
                "message", "Login successful",
                "patientId", existing.getPatientId(),
                "name", existing.getName(),
                "age", existing.getAge(),
                "bloodGroup", existing.getBloodGroup(),
                "medicalDetails", existing.getMedicalDetails(),
                "emergencyContact", existing.getEmergencyContact()
        ));
    }

    // -------------------------------
    // 3️⃣ Fetch Patient by QR (patientId)
    // -------------------------------
    @GetMapping("/by-id/{patientId}")
    public ResponseEntity<?> getPatientById(@PathVariable String patientId) {
        Optional<Patient> patient = patientRepo.findByPatientId(patientId);
        if (patient.isEmpty()) {
            return ResponseEntity.status(404).body(Map.of("error", "Patient not found"));
        }

        Patient p = patient.get();
        Map<String, Object> data = new HashMap<>();
        data.put("name", p.getName());
        data.put("age", p.getAge());
        data.put("bloodGroup", p.getBloodGroup());
        data.put("medicalDetails", p.getMedicalDetails());
        data.put("emergencyContact", p.getEmergencyContact());
        data.put("email", p.getEmail());

        return ResponseEntity.ok(data);
    }

}
