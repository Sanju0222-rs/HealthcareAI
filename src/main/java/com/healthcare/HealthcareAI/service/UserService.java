package com.healthcare.HealthcareAI.service;

import com.healthcare.HealthcareAI.entity.User;
import com.healthcare.HealthcareAI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // REGISTER USER (with encrypted password)
    public User register(User user) {

        // encrypt password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return repo.save(user);
    }

    // LOGIN USER (with password match check)
    public User login(String email, String password) {

        Optional<User> user = repo.findByEmail(email);

        if (user.isPresent()) {

            // compare raw password with encrypted password
            if (passwordEncoder.matches(password, user.get().getPassword())) {
                return user.get();
            }
        }

        return null;
    }
}