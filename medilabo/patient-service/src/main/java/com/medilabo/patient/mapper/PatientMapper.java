package com.medilabo.patient.mapper;

import com.medilabo.patient.dto.PatientDto;
import com.medilabo.patient.model.Gender;
import com.medilabo.patient.model.Patient;

/**
 * Utility class to map between domain entities and DTOs.  Keeping mapping
 * logic in a dedicated component centralises conversions and keeps the
 * controller clean.
 */
public final class PatientMapper {

    private PatientMapper() {
        // utility class
    }

    /**
     * Convert a {@link Patient} entity to a {@link PatientDto}.  The gender
     * is represented by its code ("M" or "F").
     *
     * @param patient entity to convert
     * @return a new DTO with copied fields
     */
    public static PatientDto toDto(Patient patient) {
        if (patient == null) {
            return null;
        }
        PatientDto dto = new PatientDto();
        dto.setId(patient.getId());
        dto.setLastName(patient.getLastName());
        dto.setFirstName(patient.getFirstName());
        dto.setBirthDate(patient.getBirthDate());
        dto.setGender(patient.getGender().getCode());
        dto.setAddress(patient.getAddress());
        dto.setPhone(patient.getPhone());
        return dto;
    }

    /**
     * Convert a {@link PatientDto} to a {@link Patient} entity.  If the DTO
     * contains an id it will be copied so that JPA performs an update
     * instead of an insert.
     *
     * @param dto data transfer object to convert
     * @return a new entity with copied fields
     */
    public static Patient toEntity(PatientDto dto) {
        if (dto == null) {
            return null;
        }
        Patient patient = new Patient();
        patient.setId(dto.getId());
        patient.setLastName(dto.getLastName());
        patient.setFirstName(dto.getFirstName());
        patient.setBirthDate(dto.getBirthDate());
        patient.setGender(Gender.fromCode(dto.getGender()));
        patient.setAddress(dto.getAddress());
        patient.setPhone(dto.getPhone());
        return patient;
    }
}