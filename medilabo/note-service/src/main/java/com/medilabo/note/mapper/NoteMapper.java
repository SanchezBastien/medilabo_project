package com.medilabo.note.mapper;

import com.medilabo.note.dto.NoteDto;
import com.medilabo.note.model.Note;

/**
 * Utility for converting between {@link Note} entities and {@link NoteDto}
 * transfer objects.
 */
public final class NoteMapper {

    private NoteMapper() {
    }

    public static NoteDto toDto(Note note) {
        if (note == null) {
            return null;
        }
        NoteDto dto = new NoteDto();
        dto.setId(note.getId());
        dto.setPatientId(note.getPatientId());
        dto.setNote(note.getNote());
        return dto;
    }

    public static Note toEntity(NoteDto dto) {
        if (dto == null) {
            return null;
        }
        Note note = new Note();
        note.setId(dto.getId());
        note.setPatientId(dto.getPatientId());
        note.setNote(dto.getNote());
        return note;
    }
}