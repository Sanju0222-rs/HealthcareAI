package com.healthcare.HealthcareAI.controller;

import com.healthcare.HealthcareAI.entity.Doctor;
import com.healthcare.HealthcareAI.service.DoctorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorService service;

    @GetMapping("/")
    public String doctors(Model model){

        model.addAttribute("doctors",
                service.getAllDoctors());

        return "doctor-list";
    }

   @GetMapping("/addDoctor")
public String addDoctorPage(Model model){

    model.addAttribute("doctor", new Doctor());

    return "add-doctor";
}

    @PostMapping("/saveDoctor")
    public String saveDoctor(@ModelAttribute Doctor doctor){

        service.saveDoctor(doctor);

        return "redirect:/doctors/";
    }
    @GetMapping("/search")
public String searchDoctors(@RequestParam String keyword, Model model){

    model.addAttribute("doctors",
            service.searchDoctors(keyword));

    return "doctor-list";
}

@GetMapping("/delete/{id}")
public String deleteDoctor(@PathVariable Long id){

    service.deleteDoctor(id);

    return "redirect:/doctors/";
}

@GetMapping("/edit/{id}")
public String editDoctorPage(@PathVariable Long id, Model model){

    Doctor doctor = service.getDoctorById(id);

    model.addAttribute("doctor", doctor);

    return "edit-doctor";
}

@PostMapping("/updateDoctor")
public String updateDoctor(@ModelAttribute Doctor doctor){

    service.updateDoctor(doctor);

    return "redirect:/doctors/";
}
}