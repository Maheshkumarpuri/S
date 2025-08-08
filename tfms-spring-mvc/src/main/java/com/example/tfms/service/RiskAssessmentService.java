package com.example.tfms.service;

import com.example.tfms.model.RiskAssessment;
import com.example.tfms.repository.RiskAssessmentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Service
public class RiskAssessmentService {
    private final RiskAssessmentRepository repository;
    private final ObjectMapper objectMapper;

    public RiskAssessmentService(RiskAssessmentRepository repository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    public RiskAssessment analyze(String transactionReference, String riskFactorsJson) {
        BigDecimal score = calculateScore(riskFactorsJson);
        RiskAssessment assessment = new RiskAssessment();
        assessment.setTransactionReference(transactionReference);
        assessment.setRiskFactorsJson(riskFactorsJson);
        assessment.setRiskScore(score);
        assessment.setAssessmentDate(LocalDate.now());
        return repository.save(assessment);
    }

    public RiskAssessment findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Risk assessment not found: " + id));
    }

    private BigDecimal calculateScore(String json) {
        try {
            JsonNode root = objectMapper.readTree(json == null || json.isBlank() ? "{}" : json);
            double base = 50.0;
            double countryRisk = root.path("countryRisk").asDouble(0.0);     // 0-10
            double amount = root.path("amount").asDouble(0.0);               // absolute
            double docsCompleteness = root.path("docsCompleteness").asDouble(1.0); // 0-1

            double amountFactor = Math.min(amount / 1_000_000.0, 1.0) * 20.0; // max 20
            double countryFactor = countryRisk * 2.0; // max 20
            double docsFactor = (1.0 - Math.max(Math.min(docsCompleteness, 1.0), 0.0)) * 30.0; // max 30

            double finalScore = Math.max(0.0, Math.min(100.0, base + amountFactor + countryFactor + docsFactor - 25.0));
            return BigDecimal.valueOf(finalScore).setScale(2, RoundingMode.HALF_UP);
        } catch (JsonProcessingException e) {
            return BigDecimal.valueOf(75.00);
        }
    }
}