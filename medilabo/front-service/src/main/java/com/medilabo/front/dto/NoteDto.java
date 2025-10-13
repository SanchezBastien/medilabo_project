package com.medilabo.front.dto;

import lombok.Data;

/**
 * DTO for notes used by the front-end.
 */
@Data
public class NoteDto {
    private String id;
    private Long patientId;
    private String note;
}