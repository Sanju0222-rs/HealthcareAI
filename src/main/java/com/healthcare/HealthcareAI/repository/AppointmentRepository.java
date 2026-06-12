package com.healthcare.HealthcareAI.repository;

import com.healthcare.HealthcareAI.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByPatientNameContainingIgnoreCase(String keyword);

    List<Appointment> findByDoctorNameContainingIgnoreCase(String doctorName);
}