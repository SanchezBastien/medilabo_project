package com.medilabo.patient.repository;

import com.medilabo.patient.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data repository for {@link Patient} entities.  Extending
 * {@link JpaRepository} provides CRUD operations and paging out of the box.
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    /**
     * Find patients by last name in a case‑insensitive manner.  Spring Data JPA
     * automatically generates the query based on the method name.
     *
     * @param lastName the last name to search for
     * @return list of matching patients
     */
    List<Patient> findByLastNameIgnoreCase(String lastName);
}