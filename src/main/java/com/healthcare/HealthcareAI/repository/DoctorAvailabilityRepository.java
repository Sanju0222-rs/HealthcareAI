package com.healthcare.HealthcareAI.repository;

import com.healthcare.HealthcareAI.entity.DoctorAvailability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorAvailabilityRepository
        extends JpaRepository<DoctorAvailability, Long> {

    List<DoctorAvailability> findByDoctorNameContainingIgnoreCaseAndStatus(
            String doctorName,
            String status);
}