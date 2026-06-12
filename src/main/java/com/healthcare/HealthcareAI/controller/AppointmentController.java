package com.healthcare.HealthcareAI.controller;

import com.healthcare.HealthcareAI.entity.Appointment;
import com.healthcare.HealthcareAI.service.AppointmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService service;

    @GetMapping({"","/"})
    public String appointments(Model model){

        model.addAttribute(
                "appointments",
                service.getAllAppointments());

        return "appointment-list";
    }

    @GetMapping("/addAppointment")
    public String addAppointment(Model model){

        model.addAttribute(
                "appointment",
                new Appointment());

        return "add-appointment";
    }

    @PostMapping("/saveAppointment")
    public String saveAppointment(
            @ModelAttribute Appointment appointment){

        service.saveAppointment(appointment);

        return "redirect:/appointments/";
    }
    @GetMapping("/edit/{id}")
public String editAppointmentPage(@PathVariable Long id, Model model){

    Appointment appointment = service.getAppointmentById(id);

    model.addAttribute("appointment", appointment);

    return "edit-appointment";

}
 @PostMapping("/updateAppointment")
public String updateAppointment(@ModelAttribute Appointment appointment){

    service.updateAppointment(appointment);

    return "redirect:/appointments/";
}
@GetMapping("/delete/{id}")
public String deleteAppointment(@PathVariable Long id){

    service.deleteAppointment(id);

    return "redirect:/appointments/";
}
@GetMapping("/search")
public String searchAppointments(@RequestParam String keyword, Model model){

    model.addAttribute("appointments",
            service.searchAppointments(keyword));

    return "appointment-list";
}
}