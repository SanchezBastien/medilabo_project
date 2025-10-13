package com.medilabo.risk.client;

import com.medilabo.risk.dto.PatientInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "patientClient",
        url = "${patient.service.url}",
        configuration = com.medilabo.risk.config.FeignConfig.class
)
public interface PatientClient {
    @GetMapping("/api/patients/{id}")
    PatientInfo getPatientById(@PathVariable("id") Long id);
}