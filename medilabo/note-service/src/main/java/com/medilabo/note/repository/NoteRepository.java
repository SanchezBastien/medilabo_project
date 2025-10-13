package com.medilabo.note.repository;

import com.medilabo.note.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for persisting {@link Note} documents in MongoDB.
 */
@Repository
public interface NoteRepository extends MongoRepository<Note, String> {
    /**
     * Find all notes belonging to a given patient.
     *
     * @param patientId the patient identifier
     * @return list of notes for that patient
     */
    List<Note> findByPatientId(Long patientId);
}