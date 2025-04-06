package com.rest1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Rest1Config {

    // Configuration beans can be defined here if needed
    // For example, you can define a RestTemplate bean for making REST calls

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // Permet l'accès sans authentification pour toutes les requêtes
                )
                .csrf(csrf -> csrf.disable()); // Désactive la protection CSRF (souvent nécessaire pour les API)
        return http.build(); // Retourne le SecurityFilterChain
    }

}
