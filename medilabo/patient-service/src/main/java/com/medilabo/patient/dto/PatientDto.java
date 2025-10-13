package com.medilabo.patient.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;

import java.time.LocalDate;

/**
 * Data transfer object for exposing patient information over the wire.  Only
 * includes fields relevant to clients; internal JPA details are hidden.
 */
@Data
public class PatientDto {

    private Long id;

    @NotBlank
    private String lastName;

    @NotBlank
    private String firstName;

    @NotNull
    @Past
    private LocalDate birthDate;

    @NotBlank
    private String gender;

    private String address;

    private String phone;
}