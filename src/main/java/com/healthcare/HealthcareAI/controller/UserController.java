

package com.healthcare.HealthcareAI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.healthcare.HealthcareAI.entity.User;
import com.healthcare.HealthcareAI.repository.UserRepository;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/saveUser")
    public String saveUser(User user) {

        userRepository.save(user);

        return "login";
    }
}

