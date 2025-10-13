package com.medilabo.risk.dto;

import com.medilabo.risk.model.RiskLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AssessmentDto {
    private Long patientId;
    private String firstName;
    private String lastName;
    private int age;
    private int triggerCount;
    private RiskLevel riskLevel;
}