package com.medilabo.front.client;

import com.medilabo.front.dto.AssessmentDto;
import com.medilabo.front.dto.NoteDto;
import com.medilabo.front.dto.PatientDto;
import com.medilabo.front.form.NoteForm;
import com.medilabo.front.form.PatientForm;
import jakarta.validation.Valid;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

/**
 * Simple client encapsulating HTTP calls to the gateway.  It uses
 * {@link RestTemplate} under the hood and serializes JSON payloads.
 */
@Service
public class GatewayClient {
    private final RestTemplate restTemplate;
    private final String baseUrl = "http://localhost:8080";

    public GatewayClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<PatientDto> getPatients() {
        try {
            return restTemplate.exchange(
                    baseUrl + "/api/patients",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<PatientDto>>() {
                    }
            ).getBody();
        } catch (RestClientException e) {
            return Collections.emptyList();
        }
    }

    public PatientDto getPatient(Long id) {
        return restTemplate.getForObject(baseUrl + "/api/patients/" + id, PatientDto.class);
    }

    public PatientDto createPatient(@Valid PatientForm form) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PatientForm> request = new HttpEntity<>(form, headers);
        return restTemplate.postForObject(baseUrl + "/api/patients", request, PatientDto.class);
    }

    public PatientDto updatePatient(Long id, @Valid PatientForm form) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PatientForm> request = new HttpEntity<>(form, headers);
        return restTemplate.exchange(baseUrl + "/api/patients/" + id, HttpMethod.PUT, request, PatientDto.class).getBody();
    }

    public List<NoteDto> getNotes(Long patientId) {
        try {
            return restTemplate.exchange(
                    baseUrl + "/api/notes/" + patientId,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<NoteDto>>() {
                    }
            ).getBody();
        } catch (RestClientException e) {
            return Collections.emptyList();
        }
    }

    public NoteDto createNote(@Valid NoteForm form) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<NoteForm> request = new HttpEntity<>(form, headers);
        return restTemplate.postForObject(baseUrl + "/api/notes", request, NoteDto.class);
    }

    public AssessmentDto getAssessment(Long patientId) {
        return restTemplate.getForObject(baseUrl + "/api/assessment/" + patientId, AssessmentDto.class);
    }

    public NoteDto updateNote(String id, Long patientId, String note) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        NoteDto payload = new NoteDto();
        payload.setId(id);
        payload.setPatientId(patientId);
        payload.setNote(note);

        HttpEntity<NoteDto> request = new HttpEntity<>(payload, headers);

        // Le note-service expose PUT /api/notes/{id}
        return restTemplate
                .exchange(baseUrl + "/api/notes/" + id, HttpMethod.PUT, request, NoteDto.class)
                .getBody();
    }

    public void deleteNote(String id) {
        try {
            restTemplate.delete(baseUrl + "/api/notes/" + id); // note-service: DELETE /api/notes/{id}
        } catch (RestClientException ex) {
            throw ex; // on laissera le Controller afficher un message
        }
    }
    public void deletePatient(Long id) {
        try {
            restTemplate.delete(baseUrl + "/api/patients/" + id); // gateway -> patient-service
        } catch (RestClientException ex) {
            throw ex;
        }
    }

}