package com.healthcare.HealthcareAI.controller;

import com.healthcare.HealthcareAI.service.AppointmentService;
import com.healthcare.HealthcareAI.service.DashboardService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @Autowired
    private DashboardService dashboardService;

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/admin")
    public String adminDashboard(HttpSession session, Model model){

        String role = (String) session.getAttribute("userRole");

        if(role == null || !role.equalsIgnoreCase("ADMIN")){
            return "redirect:/login";
        }

        model.addAttribute(
                "doctorCount",
                dashboardService.totalDoctors());

        model.addAttribute(
                "patientCount",
                dashboardService.totalPatients());

        model.addAttribute(
                "appointmentCount",
                dashboardService.totalAppointments());

        model.addAttribute(
                "revenue",
                dashboardService.totalRevenue());

        model.addAttribute(
                "recentAppointments",
                appointmentService.getAllAppointments());

        return "admin-dashboard";
    }
}