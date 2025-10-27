package com.example.tfms.service;

import com.example.tfms.model.Compliance;
import com.example.tfms.model.enums.ComplianceStatus;
import com.example.tfms.repository.ComplianceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ComplianceService {
    private final ComplianceRepository repository;

    public ComplianceService(ComplianceRepository repository) {
        this.repository = repository;
    }

    public Compliance generateReport(String transactionReference, boolean sanctionsHit, boolean documentsComplete) {
        Compliance report = new Compliance();
        report.setTransactionReference(transactionReference);
        boolean compliant = !sanctionsHit && documentsComplete;
        report.setComplianceStatus(compliant ? ComplianceStatus.COMPLIANT : ComplianceStatus.NON_COMPLIANT);
        report.setRemarks(compliant ? "All checks passed" : "Sanctions hit or missing documents");
        report.setReportDate(LocalDate.now());
        return repository.save(report);
    }

    public Compliance findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Compliance report not found: " + id));
    }
}