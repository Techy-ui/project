package com.example.codecare.repository;

import com.example.codecare.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    // Add this method to fix the error
    Patient findByQrCode(String qrCode);
}
