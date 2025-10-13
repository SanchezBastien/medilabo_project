package com.medilabo.front.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Form backing object for creating a new medical note.
 */
@Data
public class NoteForm {
    @NotNull
    private Long patientId;

    @NotBlank(message = "Le texte de la note est obligatoire")
    private String note;
}