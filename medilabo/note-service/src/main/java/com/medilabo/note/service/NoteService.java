package com.medilabo.note.service;

import com.medilabo.note.model.Note;
import com.medilabo.note.repository.NoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service providing business operations on {@link Note} documents.  Mongo
 * repositories return fully initialised objects so the service logic
 * remains minimal.
 */
@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Transactional(readOnly = true)
    public List<Note> findByPatientId(Long patientId) {
        return noteRepository.findByPatientId(patientId);
    }

    @Transactional
    public Note save(Note note) {
        return noteRepository.save(note);
    }

    public boolean exists(String id) {
        return noteRepository.existsById(id);
    }
    public void delete(String id) {
        noteRepository.deleteById(id);
    }
}