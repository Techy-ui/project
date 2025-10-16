package com.example.codecare.service;

import com.example.codecare.model.Hospital;
import com.example.codecare.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class HospitalService {

    @Autowired
    private HospitalRepository hospitalRepo;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public Hospital registerHospital(Hospital hospital) {
        // Encrypt password
        hospital.setPassword(encoder.encode(hospital.getPassword()));

        // Generate next hospital ID
        String lastId = hospitalRepo.findLastHospitalId();
        String nextId = generateNextHospitalId(lastId);
        hospital.setHospitalId(nextId);

        // Save hospital
        return hospitalRepo.save(hospital);
    }

    private String generateNextHospitalId(String lastId) {
        if (lastId == null || lastId.isEmpty()) {
            return "HOSP001";
        }
        int lastNumber = Integer.parseInt(lastId.replace("HOSP", ""));
        return String.format("HOSP%03d", lastNumber + 1);
    }
}
