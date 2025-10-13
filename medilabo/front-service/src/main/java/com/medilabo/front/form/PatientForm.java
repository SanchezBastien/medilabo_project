package com.medilabo.front.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

/**
 * Form backing object for creating or updating patients via the front-end.
 */
@Data
public class PatientForm {
    private Long id;

    @NotBlank(message = "Nom obligatoire")
    private String lastName;

    @NotBlank(message = "Prénom obligatoire")
    private String firstName;

    @NotNull(message = "Date de naissance obligatoire")
    @Past(message = "La date de naissance doit être dans le passé")
    private LocalDate birthDate;

    @NotBlank(message = "Sexe obligatoire (M ou F)")
    @Pattern(regexp = "[MF]", message = "Sexe doit être 'M' ou 'F'")
    private String gender;

    private String address;

    private String phone;
}