package com.medilabo.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the API gateway.  This service delegates incoming
 * requests to the appropriate backend microservice based on the request
 * path.  The routing rules are defined in {@code application.yml}.
 */
@SpringBootApplication
public class GatewayServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayServiceApplication.class, args);
    }
}