package com.medilabo.risk.controller;

import com.medilabo.risk.dto.AssessmentDto;
import com.medilabo.risk.service.AssessmentService;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assessment")
@CrossOrigin(origins = "*")
public class RiskController {

    private final AssessmentService service;

    public RiskController(AssessmentService service) {
        this.service = service;
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<AssessmentDto> assessRisk(@PathVariable("patientId") Long patientId) {
        return ResponseEntity.ok(service.evaluate(patientId));
    }
}