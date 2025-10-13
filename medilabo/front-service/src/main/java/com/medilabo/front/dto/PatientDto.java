package com.medilabo.front.dto;

import lombok.Data;

import java.time.LocalDate;

/**
 * DTO representing a patient in the front-end.  Aligns with the
 * structure returned by the patient service.
 */
@Data
public class PatientDto {
    private Long id;
    private String lastName;
    private String firstName;
    private LocalDate birthDate;
    private String gender;
    private String address;
    private String phone;
}