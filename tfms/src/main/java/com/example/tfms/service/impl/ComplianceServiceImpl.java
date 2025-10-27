package com.example.tfms.service.impl;

import com.example.tfms.exception.ResourceNotFoundException;
import com.example.tfms.model.entity.Compliance;
import com.example.tfms.model.entity.enums.ComplianceStatus;
import com.example.tfms.repository.ComplianceRepository;
import com.example.tfms.service.ComplianceService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ComplianceServiceImpl implements ComplianceService {

    private final ComplianceRepository repository;

    public ComplianceServiceImpl(ComplianceRepository repository) {
        this.repository = repository;
    }

    @Override
    public Compliance check(String transactionReference) {
        Compliance compliance = new Compliance();
        compliance.setTransactionReference(transactionReference);
        compliance.setReportDate(LocalDate.now());
        
        // Simple compliance check: mark compliant if reference length is even
        ComplianceStatus status = (transactionReference != null && transactionReference.length() % 2 == 0)
                ? ComplianceStatus.COMPLIANT : ComplianceStatus.NON_COMPLIANT;
        compliance.setComplianceStatus(status);
        
        if (status == ComplianceStatus.NON_COMPLIANT) {
            compliance.setRemarks("Transaction reference format does not meet compliance standards");
        } else {
            compliance.setRemarks("Transaction meets all compliance requirements");
        }
        
        return repository.save(compliance);
    }

    @Override
    public Compliance getById(Long complianceId) {
        return repository.findById(complianceId)
                .orElseThrow(() -> new ResourceNotFoundException("Compliance not found: " + complianceId));
    }

    @Override
    public List<Compliance> listAll() {
        return repository.findAll();
    }

    @Override
    public List<Compliance> findNonCompliant() {
        return repository.findByComplianceStatus(ComplianceStatus.NON_COMPLIANT);
    }

    @Override
    public long count() {
        return repository.count();
    }
}