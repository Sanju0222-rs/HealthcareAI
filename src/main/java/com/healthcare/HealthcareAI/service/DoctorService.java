package com.healthcare.HealthcareAI.service;

import com.healthcare.HealthcareAI.entity.Doctor;
import com.healthcare.HealthcareAI.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository repository;

    public Doctor saveDoctor(Doctor doctor){
        return repository.save(doctor);
    }

    public List<Doctor> getAllDoctors(){
        return repository.findAll();
    }

    public List<Doctor> searchDoctors(String keyword){
    return repository.findByNameContainingIgnoreCase(keyword);
   }

    public void deleteDoctor(Long id){
    repository.deleteById(id);
   }
   public Doctor getDoctorById(Long id){
    return repository.findById(id).orElse(null);
}

public Doctor updateDoctor(Doctor doctor){
    return repository.save(doctor);
}
}