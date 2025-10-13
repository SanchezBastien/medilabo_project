package com.medilabo.front.dto;

import com.medilabo.front.model.RiskLevel;
import lombok.Data;

/**
 * DTO representing the result of a risk assessment.
 */
@Data
public class AssessmentDto {
    private Long patientId;
    private String firstName;
    private String lastName;
    private int age;
    private int triggerCount;
    private RiskLevel riskLevel;
}