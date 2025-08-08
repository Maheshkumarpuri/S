package com.example.tfms.service.impl;

import com.example.tfms.model.entity.RiskAssessment;
import com.example.tfms.repository.RiskAssessmentRepository;
import com.example.tfms.service.RiskAssessmentService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class RiskAssessmentServiceImpl implements RiskAssessmentService {

    private final RiskAssessmentRepository repository;

    public RiskAssessmentServiceImpl(RiskAssessmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public RiskAssessment analyze(String transactionReference) {
        RiskAssessment assessment = new RiskAssessment();
        assessment.setTransactionReference(transactionReference);
        assessment.setAssessmentDate(LocalDate.now());
        
        // Simple risk scoring algorithm based on transaction reference
        String riskFactors = "{\"country_risk\":\"medium\",\"credit_risk\":\"low\",\"market_risk\":\"high\"}";
        assessment.setRiskFactorsJson(riskFactors);
        
        // Calculate risk score based on transaction reference length and hash
        int refLength = transactionReference != null ? transactionReference.length() : 0;
        int hashCode = transactionReference != null ? Math.abs(transactionReference.hashCode()) : 0;
        BigDecimal score = BigDecimal.valueOf((refLength + (hashCode % 50)) / 10.0);
        assessment.setRiskScore(score);
        
        return repository.save(assessment);
    }

    @Override
    public RiskAssessment getById(Long riskId) {
        return repository.findById(riskId)
                .orElseThrow(() -> new RuntimeException("RiskAssessment not found: " + riskId));
    }

    @Override
    public List<RiskAssessment> listAll() {
        return repository.findAll();
    }

    @Override
    public long count() {
        return repository.count();
    }
}