package com.healthcare.HealthcareAI.service;

import com.healthcare.HealthcareAI.entity.DoctorAvailability;
import com.healthcare.HealthcareAI.repository.DoctorAvailabilityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorAvailabilityService {

    @Autowired
    private DoctorAvailabilityRepository repository;

    public DoctorAvailability saveAvailability(
            DoctorAvailability availability) {

        availability.setStatus("AVAILABLE");

        return repository.save(availability);
    }

    public List<DoctorAvailability> getAllAvailability() {
        return repository.findAll();
    }

    public List<DoctorAvailability> getAvailableSlotsByDoctor(
            String doctorName) {

        return repository
                .findByDoctorNameContainingIgnoreCaseAndStatus(
                        doctorName,
                        "AVAILABLE");
    }
}