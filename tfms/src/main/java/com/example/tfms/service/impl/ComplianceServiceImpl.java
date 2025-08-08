package com.example.tfms.service.impl;

import com.example.tfms.exception.ResourceNotFoundException;
import com.example.tfms.model.entity.Compliance;
import com.example.tfms.model.entity.enums.ComplianceStatus;
import com.example.tfms.repository.ComplianceRepository;
import com.example.tfms.service.ComplianceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplianceServiceImpl implements ComplianceService {

    private final ComplianceRepository repository;

    public ComplianceServiceImpl(ComplianceRepository repository) {
        this.repository = repository;
    }

    @Override
    public Compliance generateReport(String transactionReference) {
        Compliance report = new Compliance();
        report.setTransactionReference(transactionReference);
        // Placeholder logic: mark compliant if ref length is even
        ComplianceStatus status = (transactionReference != null && transactionReference.length() % 2 == 0)
                ? ComplianceStatus.COMPLIANT : ComplianceStatus.NON_COMPLIANT;
        report.setComplianceStatus(status);
        report.setRemarks("Auto-generated compliance report");
        return repository.save(report);
    }

    @Override
    public Compliance submitReport(Compliance report) {
        return repository.save(report);
    }

    @Override
    public Compliance getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Compliance not found: " + id));
    }

    @Override
    public List<Compliance> listAll() {
        return repository.findAll();
    }
}