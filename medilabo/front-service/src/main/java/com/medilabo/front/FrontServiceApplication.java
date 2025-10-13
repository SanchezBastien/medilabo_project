package com.medilabo.front;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the front-end service.  This service renders HTML
 * pages using Thymeleaf templates and acts as the user interface for the
 * MédiLabo microservices ecosystem.
 */
@SpringBootApplication
public class FrontServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(FrontServiceApplication.class, args);
    }
}