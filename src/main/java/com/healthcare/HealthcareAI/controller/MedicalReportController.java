package com.healthcare.HealthcareAI.controller;

import com.healthcare.HealthcareAI.entity.MedicalReport;
import com.healthcare.HealthcareAI.service.MedicalReportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reports")
public class MedicalReportController {

    @Autowired
    private MedicalReportService service;

    @GetMapping("/")
    public String reports(Model model){

        model.addAttribute(
                "reports",
                service.getAllReports());

        return "report-list";
    }

    @GetMapping("/addReport")
    public String addReport(Model model){

        model.addAttribute(
                "report",
                new MedicalReport());

        return "add-report";
    }

    @PostMapping("/saveReport")
    public String saveReport(
            @ModelAttribute MedicalReport report){

        service.saveReport(report);

        return "redirect:/reports/";
    }
    @GetMapping("/edit/{id}")
public String editReportPage(@PathVariable Long id, Model model){

    MedicalReport report = service.getReportById(id);

    model.addAttribute("report", report);

    return "edit-report";
}

@PostMapping("/updateReport")
public String updateReport(@ModelAttribute MedicalReport report){

    service.updateReport(report);

    return "redirect:/reports/";
}

@GetMapping("/delete/{id}")
public String deleteReport(@PathVariable Long id){

    service.deleteReport(id);

    return "redirect:/reports/";
}
@GetMapping("/search")
public String searchReports(@RequestParam String keyword, Model model){

    model.addAttribute("reports",
            service.searchReports(keyword));

    return "report-list";
}
}