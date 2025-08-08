package com.example.tfms.service;

import com.example.tfms.model.entity.Compliance;

import java.util.List;

public interface ComplianceService {
    Compliance check(String transactionReference);
    Compliance getById(Long complianceId);
    List<Compliance> listAll();
    List<Compliance> findNonCompliant();
    long count();
}