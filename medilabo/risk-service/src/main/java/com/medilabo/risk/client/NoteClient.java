package com.medilabo.risk.client;

import com.medilabo.risk.dto.NoteInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(
        name = "noteClient",
        url = "${note.service.url}",
        configuration = com.medilabo.risk.config.FeignConfig.class
)
public interface NoteClient {
    @GetMapping("/api/notes/{patientId}")
    List<NoteInfo> getNotesByPatientId(@PathVariable("patientId") Long patientId);
}