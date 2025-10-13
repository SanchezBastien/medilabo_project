package com.medilabo.risk.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class PatientInfo {
    private Long id;
    private String lastName;
    private String firstName;
    private LocalDate birthDate;
    private String gender;
    private String address;
    private String phone;
}