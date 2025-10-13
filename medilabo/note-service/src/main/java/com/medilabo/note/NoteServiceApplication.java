package com.medilabo.note;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the note microservice.  This service manages free‑form
 * consultation notes for patients and persists them to a MongoDB database.
 */
@SpringBootApplication
public class NoteServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NoteServiceApplication.class, args);
    }
}