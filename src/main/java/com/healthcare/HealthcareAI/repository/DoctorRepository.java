package com.healthcare.HealthcareAI.repository;

import com.healthcare.HealthcareAI.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    List<Doctor> findByNameContainingIgnoreCase(String keyword);
}