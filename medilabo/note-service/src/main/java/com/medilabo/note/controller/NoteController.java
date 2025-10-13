package com.medilabo.note.controller;

import com.medilabo.note.dto.NoteDto;
import com.medilabo.note.mapper.NoteMapper;
import com.medilabo.note.model.Note;
import com.medilabo.note.service.NoteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * REST controller exposing endpoints for managing medical notes.  All
 * endpoints reside under the "/api/notes" path.
 */
@RestController
@RequestMapping("/api/notes")
@CrossOrigin(origins = "*")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    /**
     * List all notes for a given patient.
     *
     * @param patientId the patient identifier
     * @return list of notes
     */
    @GetMapping("/{patientId}")
    public ResponseEntity<List<NoteDto>> getNotesByPatient(@PathVariable("patientId") Long patientId) {
        List<NoteDto> dtos = noteService.findByPatientId(patientId).stream()
                .map(NoteMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    /**
     * Create a new note for a patient.  The request body must contain the
     * patientId and note content.
     *
     * @param dto the note information
     * @return the persisted note
     */
    @PostMapping
    public ResponseEntity<NoteDto> createNote(@Valid @RequestBody NoteDto dto) {
        // Ensure id is null to generate a new one
        dto.setId(null);
        Note saved = noteService.save(NoteMapper.toEntity(dto));
        return new ResponseEntity<>(NoteMapper.toDto(saved), HttpStatus.CREATED);
    }
}