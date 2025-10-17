package com.example.codecare.service;

import com.example.codecare.model.Patient;
import com.example.codecare.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    // Get patient by QR code
    public Patient getPatientByQrCode(String qrCode) {
        // Returns the patient if found, otherwise null
        return patientRepository.findByQrCode(qrCode).orElse(null);
    }

    // Save a patient
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    // Get all patients
    public Iterable<Patient> getAllPatients() {
        return patientRepository.findAll();
    }
}
