package com.healthcare.HealthcareAI.repository;

import com.healthcare.HealthcareAI.entity.MedicalReport;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MedicalReportRepository
        extends JpaRepository<MedicalReport, Long> {

    List<MedicalReport> findByPatientNameContainingIgnoreCase(String patientName);
}