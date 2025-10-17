package com.example.codecare.repository;

import com.example.codecare.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    // Find patient by QR code ID
    Optional<Patient> findByPatientId(String patientId);

    // Find by email for login
    Optional<Patient> findByEmail(String email);
}
