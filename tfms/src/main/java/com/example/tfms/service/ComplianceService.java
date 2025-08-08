package com.example.tfms.service;

import com.example.tfms.model.entity.Compliance;

import java.util.List;

public interface ComplianceService {
    Compliance generateReport(String transactionReference);
    Compliance submitReport(Compliance report);
    Compliance getById(Long id);
    List<Compliance> listAll();
}