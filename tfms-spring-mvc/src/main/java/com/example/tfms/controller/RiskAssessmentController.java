package com.example.tfms.controller;

import com.example.tfms.model.RiskAssessment;
import com.example.tfms.service.RiskAssessmentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/risks")
public class RiskAssessmentController {

    private final RiskAssessmentService service;
    private final ObjectMapper objectMapper;

    public RiskAssessmentController(RiskAssessmentService service, ObjectMapper objectMapper) {
        this.service = service;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/analyze")
    public ResponseEntity<RiskAssessment> analyze(@RequestBody Map<String, Object> payload) throws JsonProcessingException {
        String txRef = String.valueOf(payload.getOrDefault("transactionReference", "UNKNOWN"));
        Object rf = payload.get("riskFactors");
        String rfJson = rf == null ? "{}" : objectMapper.writeValueAsString(rf);
        return ResponseEntity.ok(service.analyze(txRef, rfJson));
    }

    @GetMapping("/{id}/score")
    public ResponseEntity<String> getScore(@PathVariable Long id) {
        RiskAssessment ra = service.findById(id);
        return ResponseEntity.ok(ra.getRiskScore().toPlainString());
    }
}