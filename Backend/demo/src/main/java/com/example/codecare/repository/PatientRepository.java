package com.example.codecare.repository;

import com.example.codecare.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    // Find patient by QR code (stored in patientId)
    Optional<Patient> findByPatientId(String patientId);

    // Optional: find by email for login
    Optional<Patient> findByEmail(String email);
}
