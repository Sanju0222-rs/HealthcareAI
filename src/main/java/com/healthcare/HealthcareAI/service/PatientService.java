package com.healthcare.HealthcareAI.service;

import com.healthcare.HealthcareAI.entity.Patient;
import com.healthcare.HealthcareAI.repository.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository repository;

    public Patient savePatient(Patient patient){
        return repository.save(patient);
    }

    public List<Patient> getAllPatients(){
        return repository.findAll();
    }
    public Patient getPatientById(Long id){
    return repository.findById(id).orElse(null);
}

public Patient updatePatient(Patient patient){
    return repository.save(patient);
}

public void deletePatient(Long id){
    repository.deleteById(id);
}
public List<Patient> searchPatients(String keyword){
    return repository.findByNameContainingIgnoreCase(keyword);
}
}