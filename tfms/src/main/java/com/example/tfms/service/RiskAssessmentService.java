package com.example.tfms.service;

import com.example.tfms.model.entity.RiskAssessment;

import java.util.List;

public interface RiskAssessmentService {
    RiskAssessment analyze(String transactionReference);
    RiskAssessment getById(Long riskId);
    List<RiskAssessment> listAll();
    long count();
}