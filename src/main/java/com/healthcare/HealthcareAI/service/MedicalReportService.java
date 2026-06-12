package com.healthcare.HealthcareAI.service;

import com.healthcare.HealthcareAI.entity.MedicalReport;
import com.healthcare.HealthcareAI.repository.MedicalReportRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalReportService {

    @Autowired
    private MedicalReportRepository repository;

    public MedicalReport saveReport(
            MedicalReport report){

        return repository.save(report);
    }

    public List<MedicalReport> getAllReports(){

        return repository.findAll();
    }

    public MedicalReport getReportById(Long id){
    return repository.findById(id).orElse(null);
}

public MedicalReport updateReport(MedicalReport report){
    return repository.save(report);
}

public void deleteReport(Long id){
    repository.deleteById(id);
}
public List<MedicalReport> searchReports(String keyword){
    return repository.findByPatientNameContainingIgnoreCase(keyword);
}
public List<MedicalReport> getReportsByPatient(String patientName){
    return repository.findByPatientNameContainingIgnoreCase(patientName);
}
}