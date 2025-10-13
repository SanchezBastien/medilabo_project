package com.medilabo.patient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the patient microservice.  This module exposes a set of
 * REST endpoints for managing patients' demographic information such as
 * name, birth date, gender, address and phone number.  The service is
 * intentionally simple yet follows a layered architecture with clear
 * separation between the controller, service and repository tiers.
 */
@SpringBootApplication
public class PatientServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientServiceApplication.class, args);
    }
}