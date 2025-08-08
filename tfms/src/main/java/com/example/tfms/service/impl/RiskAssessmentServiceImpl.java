package com.example.tfms.service.impl;

import com.example.tfms.model.entity.RiskAssessment;
import com.example.tfms.repository.RiskAssessmentRepository;
import com.example.tfms.service.RiskAssessmentService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class RiskAssessmentServiceImpl implements RiskAssessmentService {

    private final RiskAssessmentRepository repository;

    public RiskAssessmentServiceImpl(RiskAssessmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public RiskAssessment analyze(RiskAssessment request) {
        // Very naive scoring: length of riskFactorsJson mod 100
        int length = request.getRiskFactorsJson() == null ? 0 : request.getRiskFactorsJson().length();
        BigDecimal score = BigDecimal.valueOf(length % 100);
        request.setRiskScore(score);
        return repository.save(request);
    }

    @Override
    public RiskAssessment getById(Long riskId) {
        return repository.findById(riskId).orElseThrow(() -> new RuntimeException("RiskAssessment not found: " + riskId));
    }

    @Override
    public List<RiskAssessment> listAll() {
        return repository.findAll();
    }
}