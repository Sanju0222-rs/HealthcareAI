package com.healthcare.HealthcareAI.controller;

import com.healthcare.HealthcareAI.entity.Appointment;
import com.healthcare.HealthcareAI.service.AppointmentService;
import com.healthcare.HealthcareAI.service.MedicalReportService;
import com.healthcare.HealthcareAI.service.BillingService;
import com.healthcare.HealthcareAI.service.DoctorAvailabilityService;
import com.healthcare.HealthcareAI.service.DoctorService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PatientModuleController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private MedicalReportService medicalReportService;

    @Autowired
    private BillingService billingService;

    private boolean isPatient(HttpSession session) {
        String role = (String) session.getAttribute("userRole");
        return role != null && role.equalsIgnoreCase("PATIENT");
    }

    @GetMapping("/patient/dashboard")
    public String patientDashboard(HttpSession session) {

        if (!isPatient(session)) {
            return "redirect:/login";
        }

        return "patient-dashboard";
    }

    @GetMapping("/patient/appointments")
    public String patientAppointments(HttpSession session,
                                      Model model) {

        if (!isPatient(session)) {
            return "redirect:/login";
        }

        String patientName = (String) session.getAttribute("userName");

        model.addAttribute(
                "appointments",
                appointmentService.getAppointmentsByPatient(patientName));

        return "patient-appointments";
    }

   @Autowired
private DoctorService doctorService;

@GetMapping("/patient/bookAppointment")
public String patientBookAppointment(HttpSession session,
                                     Model model) {

    if (!isPatient(session)) {
        return "redirect:/login";
    }

    Appointment appointment = new Appointment();

    appointment.setAppointmentId(
            appointmentService.generateAppointmentId());

    model.addAttribute("appointment", appointment);

    model.addAttribute("doctors",
            doctorService.getAllDoctors());
    model.addAttribute("slots",
        doctorAvailabilityService.getAllAvailability());

    return "patient-add-appointment";
}
@GetMapping("/patient/reports")
public String patientReports(HttpSession session,
                             Model model) {

    if (!isPatient(session)) {
        return "redirect:/login";
    }

    String patientName =
            (String) session.getAttribute("userName");

    model.addAttribute(
            "reports",
            medicalReportService.getReportsByPatient(patientName));

    return "patient-reports";
}

   @GetMapping("/patient/bills")
public String patientBills(HttpSession session,
                           Model model) {

    if (!isPatient(session)) {
        return "redirect:/login";
    }

    String patientName =
            (String) session.getAttribute("userName");

    model.addAttribute(
            "bills",
            billingService.getBillsByPatient(patientName));

    return "patient-bills";
}
    @Autowired
private DoctorAvailabilityService doctorAvailabilityService;
@PostMapping("/patient/saveAppointment")
public String savePatientAppointment(HttpSession session,
                                     @ModelAttribute Appointment appointment) {

    if (!isPatient(session)) {
        return "redirect:/login";
    }

    String patientName = (String) session.getAttribute("userName");

    appointment.setPatientName(patientName);
    appointment.setStatus("PENDING");

    appointmentService.saveAppointment(appointment);

    return "redirect:/patient/appointments";
}
}