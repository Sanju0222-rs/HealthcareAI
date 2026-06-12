package com.healthcare.HealthcareAI.controller;

import com.healthcare.HealthcareAI.entity.MedicalReport;
import com.healthcare.HealthcareAI.service.AppointmentService;
import com.healthcare.HealthcareAI.service.BillingService;
import com.healthcare.HealthcareAI.service.DoctorAvailabilityService;
import com.healthcare.HealthcareAI.service.PatientService;
import com.healthcare.HealthcareAI.service.MedicalReportService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.healthcare.HealthcareAI.entity.DoctorAvailability;

@Controller
public class DoctorModuleController {
    @Autowired
private DoctorAvailabilityService doctorAvailabilityService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private MedicalReportService medicalReportService;

    private boolean isDoctor(HttpSession session) {
        String role = (String) session.getAttribute("userRole");
        return role != null && role.equalsIgnoreCase("DOCTOR");
    }
@GetMapping("/doctor/dashboard")
public String doctorDashboard(HttpSession session,
                              Model model) {

    if (!isDoctor(session)) {
        return "redirect:/login";
    }

    String doctorName =
            (String) session.getAttribute("userName");

    model.addAttribute(
            "doctorName",
            doctorName);

    model.addAttribute(
            "doctorIncome",
            billingService.getDoctorIncome(doctorName));

    return "doctor-dashboard";
}

  @GetMapping("/doctor/appointments")
public String doctorAppointments(HttpSession session,
                                 Model model) {

    if (!isDoctor(session)) {
        return "redirect:/login";
    }

    String doctorName =
            (String) session.getAttribute("userName");

    model.addAttribute(
            "appointments",
            appointmentService.getAppointmentsByDoctor(
                    doctorName));

    return "doctor-appointments";
}

    @GetMapping("/doctor/patients")
    public String doctorPatients(HttpSession session,
                                 Model model) {

        if (!isDoctor(session)) {
            return "redirect:/login";
        }

        model.addAttribute(
                "patients",
                patientService.getAllPatients());

        return "doctor-patients";
    }

    @GetMapping("/doctor/reports")
    public String doctorReports(HttpSession session,
                                Model model) {

        if (!isDoctor(session)) {
            return "redirect:/login";
        }

        model.addAttribute(
                "reports",
                medicalReportService.getAllReports());

        return "doctor-reports";
    }

    @GetMapping("/doctor/addReport")
    public String doctorAddReport(HttpSession session,
                                  Model model) {

        if (!isDoctor(session)) {
            return "redirect:/login";
        }

        model.addAttribute(
                "report",
                new MedicalReport());

        return "doctor-add-report";
    }

    @PostMapping("/doctor/saveReport")
    public String doctorSaveReport(HttpSession session,
                                   @ModelAttribute MedicalReport report) {

        if (!isDoctor(session)) {
            return "redirect:/login";
        }

        medicalReportService.saveReport(report);

        return "redirect:/doctor/reports";
    }
    @GetMapping("/doctor/approve/{id}")
public String approveAppointment(HttpSession session,
                                 @PathVariable Long id){

    if (!isDoctor(session)) {
        return "redirect:/login";
    }

    appointmentService.updateStatus(id, "APPROVED");

    return "redirect:/doctor/appointments";
}

@GetMapping("/doctor/reject/{id}")
public String rejectAppointment(HttpSession session,
                                @PathVariable Long id){

    if (!isDoctor(session)) {
        return "redirect:/login";
    }

    appointmentService.updateStatus(id, "REJECTED");

    return "redirect:/doctor/appointments";
}

@GetMapping("/doctor/complete/{id}")
public String completeAppointment(HttpSession session,
                                  @PathVariable Long id){

    if (!isDoctor(session)) {
        return "redirect:/login";
    }

    appointmentService.updateStatus(id, "COMPLETED");

    return "redirect:/doctor/appointments";
}
@GetMapping("/doctor/availability")
public String doctorAvailability(HttpSession session,
                                 Model model) {

    if (!isDoctor(session)) {
        return "redirect:/login";
    }

    model.addAttribute("availability",
            new DoctorAvailability());

    model.addAttribute("slots",
            doctorAvailabilityService.getAllAvailability());

    return "doctor-availability";
}

@PostMapping("/doctor/saveAvailability")
public String saveAvailability(HttpSession session,
                               @ModelAttribute DoctorAvailability availability) {

    if (!isDoctor(session)) {
        return "redirect:/login";
    }

    String doctorName =
            (String) session.getAttribute("userName");

    availability.setDoctorName(doctorName);

    doctorAvailabilityService.saveAvailability(availability);

    return "redirect:/doctor/availability";
}
@Autowired
private BillingService billingService;
@GetMapping("/doctor/income")
public String doctorIncome(HttpSession session, Model model){

    if (!isDoctor(session)) {
        return "redirect:/login";
    }

    String doctorName =
            (String) session.getAttribute("userName");

    model.addAttribute("bills",
            billingService.getBillsByDoctor(doctorName));

    model.addAttribute("doctorIncome",
            billingService.getDoctorIncome(doctorName));

    return "doctor-income";
}

}