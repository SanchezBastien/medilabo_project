package com.medilabo.note.config;

import com.medilabo.note.model.Note;
import com.medilabo.note.repository.NoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Simple data loader that populates the MongoDB database with the notes
 * specified in the sprint documentation when the application starts.  The
 * loader checks whether the repository is empty before inserting to avoid
 * duplicating data on restart.
 */
@Configuration
public class DataInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataInitializer.class);

    @Bean
    CommandLineRunner initDatabase(NoteRepository repository) {
        return args -> {
            if (repository.count() > 0) {
                LOGGER.info("Skipping test data insertion; {} notes already exist", repository.count());
                return;
            }
            // Insert test notes as per sprint specification
            List<Note> seed = List.of(
                    note(1L, "Le patient déclare qu'il 'se sent très bien' Poids égal ou inférieur au poids recommandé"),
                    note(2L, "Le patient déclare qu'il ressent beaucoup de stress au travail Il se plaint également que son audition est anormale dernièrement"),
                    note(2L, "Le patient déclare avoir fait une réaction aux médicaments au cours des 3 derniers mois Il remarque également que son audition continue d'être anormale"),
                    note(3L, "Le patient déclare qu'il fume depuis peu"),
                    note(3L, "Le patient déclare qu'il est fumeur et qu'il a cessé de fumer l'année dernière Il se plaint également de crises d'apnée respiratoire anormales Tests de laboratoire indiquant un taux de cholestérol LDL élevé"),
                    note(4L, "Le patient déclare qu'il lui est devenu difficile de monter les escaliers Il se plaint également d'être essoufflé Tests de laboratoire indiquant que les anticorps sont élevés Réaction aux médicaments"),
                    note(4L, "Le patient déclare qu'il a mal au dos lorsqu'il reste assis pendant longtemps"),
                    note(4L, "Le patient déclare avoir commencé à fumer depuis peu Hémoglobine A1C supérieure au niveau recommandé"),
                    note(4L, "Taille, Poids, Cholestérol, Vertige et Réaction")
            );
            repository.saveAll(seed);
            LOGGER.info("Inserted {} test notes", seed.size());
        };
    }

    private static Note note(Long patientId, String text) {
        Note note = new Note();
        note.setPatientId(patientId);
        note.setNote(text);
        return note;
    }
}