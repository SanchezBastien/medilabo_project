package com.medilabo.risk.dto;

import lombok.Data;

@Data
public class NoteInfo {
    private String id;
    private Long patientId;
    private String note;
}