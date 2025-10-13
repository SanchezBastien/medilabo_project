package com.medilabo.risk.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Configuration
public class FeignConfig {

    @Bean
    public RequestInterceptor basicAuthInterceptor() {
        String raw = "user:password";
        String token = Base64.getEncoder().encodeToString(raw.getBytes(StandardCharsets.UTF_8));
        return template -> template.header("Authorization", "Basic " + token);
    }
}