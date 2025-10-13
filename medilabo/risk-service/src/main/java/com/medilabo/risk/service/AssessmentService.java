package com.medilabo.risk.service;

import com.medilabo.risk.client.NoteClient;
import com.medilabo.risk.client.PatientClient;
import com.medilabo.risk.dto.AssessmentDto;
import com.medilabo.risk.dto.NoteInfo;
import com.medilabo.risk.dto.PatientInfo;
import com.medilabo.risk.model.RiskLevel;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Service
public class AssessmentService {

    private final PatientClient patientClient;
    private final NoteClient noteClient;

    private static final List<String> TRIGGERS = Arrays.asList(
            "HÉMOGLOBINE A1C", "HEMOGLOBINE A1C",
            "MICROALBUMINE",
            "TAILLE",
            "POIDS",
            "FUMEUR", "FUMEUSE", "FUMER", "FUME",
            "ANORMAL",
            "CHOLESTÉROL", "CHOLESTEROL",
            "VERTIGE", "VERTIGES",
            "RECHUTE", "RECHUTES",
            "RÉACTION", "REACTION",
            "ANTICORPS"
    );

    public AssessmentService(PatientClient patientClient, NoteClient noteClient) {
        this.patientClient = patientClient;
        this.noteClient = noteClient;
    }

    public AssessmentDto evaluate(Long patientId) {
        PatientInfo p = patientClient.getPatientById(patientId);
        List<NoteInfo> notes = noteClient.getNotesByPatientId(patientId);

        int age = computeAge(p.getBirthDate(), LocalDate.now());
        int triggerCount = countTriggers(notes);

        RiskLevel level = determineRiskLevel(age, p.getGender(), triggerCount);

        return new AssessmentDto(
                p.getId(),
                p.getFirstName(),
                p.getLastName(),
                age,
                triggerCount,
                level
        );
    }

    private static int computeAge(LocalDate birth, LocalDate now) {
        return Period.between(birth, now).getYears();
    }

    private static int countTriggers(List<NoteInfo> notes) {
        int count = 0;
        for (NoteInfo n : notes) {
            if (n.getNote() == null) continue;
            String text = normalize(n.getNote());
            for (String t : TRIGGERS) {
                if (text.contains(normalize(t))) {
                    count++;
                }
            }
        }
        return count;
    }

    private static String normalize(String s) {
        return s.toUpperCase(Locale.FRENCH)
                .replace("É", "E")
                .replace("È", "E")
                .replace("Ê", "E")
                .replace("À", "A")
                .replace("Â", "A")
                .replace("Î", "I")
                .replace("Ô", "O")
                .replace("Û", "U");
    }

    private static RiskLevel determineRiskLevel(int age, String gender, int triggers) {
        boolean male = "M".equalsIgnoreCase(gender) || "MALE".equalsIgnoreCase(gender);
        boolean female = "F".equalsIgnoreCase(gender) || "FEMALE".equalsIgnoreCase(gender);

        if (age >= 30) {
            if (triggers >= 8) return RiskLevel.EARLY_ONSET;
            if (triggers >= 6) return RiskLevel.IN_DANGER;
            if (triggers >= 2) return RiskLevel.BORDERLINE;
            return RiskLevel.NONE;
        } else {
            if (male) {
                if (triggers >= 5) return RiskLevel.EARLY_ONSET;
                if (triggers >= 3) return RiskLevel.IN_DANGER;
                return RiskLevel.NONE;
            }
            if (female) {
                if (triggers >= 7) return RiskLevel.EARLY_ONSET;
                if (triggers >= 4) return RiskLevel.IN_DANGER;
                return RiskLevel.NONE;
            }
            return RiskLevel.NONE;
        }
    }
}