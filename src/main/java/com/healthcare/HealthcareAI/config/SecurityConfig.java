package com.healthcare.HealthcareAI.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http

            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth

                .requestMatchers(
                        "/",
                        "/login",
                        "/register",
                        "/css/**",
                        "/js/**",
                        "/images/**"
                ).permitAll()

                .anyRequest().permitAll()

            )

            .logout(logout -> logout

                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")

            );

        return http.build();
    }
}