package com.healthcare.HealthcareAI.controller;

import com.healthcare.HealthcareAI.entity.User;
import com.healthcare.HealthcareAI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

  @PostMapping("/register")
public String registerUser(@ModelAttribute User user, Model model) {
    try {
        userService.register(user);
        model.addAttribute("msg", "Registration Successful!");
        return "login";
    } catch (Exception e) {
        model.addAttribute("error", "Registration failed: " + e.getMessage());
        return "register";
    }
}

   @PostMapping("/login")
public String loginUser(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {

    User user = userService.login(email, password);

    if (user == null) {
        model.addAttribute("error", "Invalid Email or Password");
        return "login";
    }

    session.setAttribute("loggedUser", user);
    session.setAttribute("userRole", user.getRole());
    session.setAttribute("userName", user.getFullname());

    if ("ADMIN".equalsIgnoreCase(user.getRole())) {
        return "redirect:/admin";
    }

    if ("DOCTOR".equalsIgnoreCase(user.getRole())) {
        return "redirect:/doctor/dashboard";
    }

    if ("PATIENT".equalsIgnoreCase(user.getRole())) {
        return "redirect:/patient/dashboard";
    }

    model.addAttribute("error", "Invalid Role");
    return "login";
}
@GetMapping("/logout")
public String logout(HttpSession session) {
    session.invalidate();
    return "redirect:/login";
}

}