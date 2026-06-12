package com.healthcare.HealthcareAI.service;

import com.healthcare.HealthcareAI.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    @Autowired
    private DoctorRepository doctorRepo;

    @Autowired
    private PatientRepository patientRepo;

    @Autowired
    private AppointmentRepository appointmentRepo;

    @Autowired
    private BillingRepository billingRepo;

    public long totalDoctors() {
        long count = doctorRepo.count();
        System.out.println("Doctors = " + count);
        return count;
    }

    public long totalPatients() {
        long count = patientRepo.count();
        System.out.println("Patients = " + count);
        return count;
    }

    public long totalAppointments() {
        long count = appointmentRepo.count();
        System.out.println("Appointments = " + count);
        return count;
    }

    public double totalRevenue() {

        Double revenue = billingRepo.getTotalRevenue();

        return revenue == null ? 0.0 : revenue;
    }
}