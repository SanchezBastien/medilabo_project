package com.medilabo.front.controller;

import com.medilabo.front.client.GatewayClient;
import com.medilabo.front.dto.AssessmentDto;
import com.medilabo.front.dto.NoteDto;
import com.medilabo.front.dto.PatientDto;
import com.medilabo.front.form.NoteForm;
import com.medilabo.front.form.PatientForm;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Web controller serving the HTML views.  It interacts with the backend
 * microservices via {@link GatewayClient} and populates the model for
 * Thymeleaf templates.
 */
@Controller
public class FrontController {

    private final GatewayClient client;

    public FrontController(GatewayClient client) {
        this.client = client;
    }

    /**
     * Home page displays the list of patients.
     */
    @GetMapping({"/", "/patients"})
    public String listPatients(Model model) {
        List<PatientDto> patients = client.getPatients();
        model.addAttribute("patients", patients);
        return "patients";
    }

    /**
     * Display form to create a new patient.
     */
    @GetMapping("/patients/new")
    public String showCreatePatientForm(Model model) {
        model.addAttribute("patientForm", new PatientForm());
        return "patient-form";
    }

    /**
     * Handle submission of the create patient form.
     */
    @PostMapping("/patients")
    public String createPatient(@Valid @ModelAttribute("patientForm") PatientForm form,
                                BindingResult result) {
        if (result.hasErrors()) {
            return "patient-form";
        }
        client.createPatient(form);
        return "redirect:/patients";
    }

    /**
     * Display details for a single patient including notes and risk.
     */
    @GetMapping("/patients/{id}")
    public String viewPatient(@PathVariable("id") Long id, Model model) {
        PatientDto patient = client.getPatient(id);
        if (patient == null) {
            return "redirect:/patients";
        }
        AssessmentDto assessment = client.getAssessment(id);
        List<NoteDto> notes = client.getNotes(id);

        model.addAttribute("patient", patient);
        model.addAttribute("assessment", assessment);
        model.addAttribute("notes", notes);
        NoteForm noteForm = new NoteForm();
        noteForm.setPatientId(id);
        model.addAttribute("noteForm", noteForm);
        // For editing patient, prepopulate form
        PatientForm patientForm = new PatientForm();
        patientForm.setId(patient.getId());
        patientForm.setLastName(patient.getLastName());
        patientForm.setFirstName(patient.getFirstName());
        patientForm.setBirthDate(patient.getBirthDate());
        patientForm.setGender(patient.getGender());
        patientForm.setAddress(patient.getAddress());
        patientForm.setPhone(patient.getPhone());
        model.addAttribute("patientForm", patientForm);
        return "patient-detail";
    }

    /**
     * Handle update of patient information from the detail page.
     */
    @PostMapping("/patients/{id}/update")
    public String updatePatient(@PathVariable("id") Long id,
                                @Valid @ModelAttribute("patientForm") PatientForm form,
                                BindingResult result) {
        if (result.hasErrors()) {
            return "patient-detail";
        }
        client.updatePatient(id, form);
        return "redirect:/patients/" + id;
    }

    /**
     * Handle creation of a new note for a patient.
     */
    @PostMapping("/patients/{id}/notes")
    public String addNote(@PathVariable("id") Long id,
                          @Valid @ModelAttribute("noteForm") NoteForm form,
                          BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/patients/" + id;
        }
        form.setPatientId(id);
        client.createNote(form);
        return "redirect:/patients/" + id;
    }

    @PostMapping("/patients/{id}/notes/{noteId}/update")
    public String updateNote(@PathVariable("id") Long id,
                             @PathVariable("noteId") String noteId,
                             @RequestParam("patientId") Long patientId,
                             @RequestParam("note") String note,
                             RedirectAttributes ra) {

        if (!id.equals(patientId)) {
            ra.addFlashAttribute("error", "Patient invalide pour la note.");
            return "redirect:/patients/" + id;
        }
        if (note == null || note.isBlank()) {
            ra.addFlashAttribute("error", "La note ne peut pas être vide.");
            return "redirect:/patients/" + id;
        }

        // effectuer la mise à jour côté backend
        client.updateNote(noteId, id, note);

        ra.addFlashAttribute("success", "Note mise à jour.");
        return "redirect:/patients/" + id;
    }
}