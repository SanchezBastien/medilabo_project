package com.medilabo.note.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * MongoDB document representing a medical note.  Each note belongs to a
 * particular patient identified by their id.  The free‑form text may
 * contain medical terminology used for diabetes risk assessment.
 */
@Document(collection = "notes")
@Data
@NoArgsConstructor
public class Note {

    @Id
    private String id;

    /**
     * Identifier of the patient who owns this note.  The service treats this
     * as opaque; it is not a foreign key constraint in MongoDB.
     */
    @NotNull
    private Long patientId;

    /**
     * The clinician's note.  Free‑form plain text with preserved formatting.
     */
    @NotNull
    private String note;
}