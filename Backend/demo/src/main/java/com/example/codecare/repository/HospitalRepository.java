package com.example.codecare.repository;

import com.example.codecare.model.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {
    Optional<Hospital> findByEmail(String email);
    Optional<Hospital> findByHospitalId(String hospitalId);

    @Query(value = "SELECT hospital_id FROM hospitals ORDER BY id DESC LIMIT 1", nativeQuery = true)
    String findLastHospitalId();
}
