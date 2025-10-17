package com.example.codecare.repository;

import com.example.codecare.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    // Now returns Optional<Patient> for safe handling
    Optional<Patient> findByQrCode(String qrCode);

    Optional<Patient> findByEmail(String email);

    Optional<Patient> findByPatientId(String patientId);
}
