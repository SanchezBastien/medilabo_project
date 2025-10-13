package com.medilabo.patient.service;

import com.medilabo.patient.model.Patient;
import com.medilabo.patient.repository.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service component encapsulating business logic around {@link Patient}
 * persistence.  Having a dedicated service layer facilitates unit
 * testing and keeps the controller free of repository concerns.
 */
@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    /**
     * Retrieve all patients from the database.
     *
     * @return list of all patients
     */
    @Transactional(readOnly = true)
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    /**
     * Look up a patient by its primary key.
     *
     * @param id patient identifier
     * @return an optional containing the found patient or empty if missing
     */
    @Transactional(readOnly = true)
    public Optional<Patient> findById(Long id) {
        return patientRepository.findById(id);
    }

    /**
     * Persist a new patient in the database.
     *
     * @param patient the patient to persist
     * @return the saved entity with its generated identifier
     */
    @Transactional
    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    /**
     * Update an existing patient.  The repository will perform an insert if
     * the identifier is null.
     *
     * @param patient the patient to update
     * @return the persisted entity
     */
    @Transactional
    public Patient update(Patient patient) {
        return patientRepository.save(patient);
    }
}