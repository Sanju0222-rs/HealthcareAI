package com.healthcare.HealthcareAI.controller;

import com.healthcare.HealthcareAI.entity.Patient;
import com.healthcare.HealthcareAI.service.PatientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService service;

    @GetMapping("/")
    public String patients(Model model){

        model.addAttribute(
                "patients",
                service.getAllPatients());

        return "patient-list";
    }

    @GetMapping("/addPatient")
    public String addPatient(Model model){

        model.addAttribute(
                "patient",
                new Patient());

        return "add-patient";
    }

    @PostMapping("/savePatient")
    public String savePatient(
            @ModelAttribute Patient patient){

        service.savePatient(patient);

        return "redirect:/patients/";
    }
    @GetMapping("/edit/{id}")
public String editPatientPage(@PathVariable Long id, Model model){

    Patient patient = service.getPatientById(id);

    model.addAttribute("patient", patient);

    return "edit-patient";
}

@PostMapping("/updatePatient")
public String updatePatient(@ModelAttribute Patient patient){

    service.updatePatient(patient);

    return "redirect:/patients/";
}

@GetMapping("/delete/{id}")
public String deletePatient(@PathVariable Long id){

    service.deletePatient(id);

    return "redirect:/patients/";
}
@GetMapping("/search")
public String searchPatients(@RequestParam String keyword, Model model){

    model.addAttribute("patients",
            service.searchPatients(keyword));

    return "patient-list";
}
}