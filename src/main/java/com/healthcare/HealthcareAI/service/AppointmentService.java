package com.healthcare.HealthcareAI.service;

import com.healthcare.HealthcareAI.entity.Appointment;
import com.healthcare.HealthcareAI.repository.AppointmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository repository;

    public Appointment saveAppointment(Appointment appointment){
        return repository.save(appointment);
    }

    public List<Appointment> getAllAppointments(){
        return repository.findAll();
    }
    public Appointment getAppointmentById(Long id){
    return repository.findById(id).orElse(null);
}

public Appointment updateAppointment(Appointment appointment){
    return repository.save(appointment);
}

public void deleteAppointment(Long id){
    repository.deleteById(id);
}
public List<Appointment> searchAppointments(String keyword){
    return repository.findByPatientNameContainingIgnoreCase(keyword);
}
public List<Appointment> getAppointmentsByDoctor(String doctorName){
    return repository.findByDoctorNameContainingIgnoreCase(doctorName);
}
public List<Appointment> getAppointmentsByPatient(String patientName){
    return repository.findByPatientNameContainingIgnoreCase(patientName);
}
public void updateStatus(Long id, String status){

    Appointment appointment = repository.findById(id).orElse(null);

    if(appointment != null){
        appointment.setStatus(status);
        repository.save(appointment);
    }
}
public String generateAppointmentId(){

    long count = repository.count() + 1;

    return "APT" + String.format("%04d", count);
}

}