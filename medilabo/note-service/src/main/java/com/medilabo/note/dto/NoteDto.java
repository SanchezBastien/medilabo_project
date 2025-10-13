package com.medilabo.note.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Data transfer object for notes.  Only contains fields exposed over the API.
 */
@Data
public class NoteDto {
    private String id;

    @NotNull
    private Long patientId;

    @NotNull
    private String note;
}