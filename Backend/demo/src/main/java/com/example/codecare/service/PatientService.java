package com.example.codecare.service;

import com.example.codecare.entity.Patient;
import com.example.codecare.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Patient getPatientByQrCode(String qrCode) {
        Optional<Patient> patient = patientRepository.findByQrCode(qrCode);
        return patient.orElse(null);
    }

    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Iterable<Patient> getAllPatients() {
        return patientRepository.findAll();
    }
}
