package com.example.tfms.service;

import com.example.tfms.model.BankGuarantee;
import com.example.tfms.model.enums.GuaranteeStatus;
import com.example.tfms.repository.BankGuaranteeRepository;
import org.springframework.stereotype.Service;

@Service
public class BankGuaranteeService {
    private final BankGuaranteeRepository repository;

    public BankGuaranteeService(BankGuaranteeRepository repository) {
        this.repository = repository;
    }

    public BankGuarantee request(BankGuarantee guarantee) {
        guarantee.setStatus(GuaranteeStatus.PENDING);
        return repository.save(guarantee);
    }

    public BankGuarantee issue(Long id) {
        return repository.findById(id).map(existing -> {
            existing.setStatus(GuaranteeStatus.ISSUED);
            return repository.save(existing);
        }).orElseThrow(() -> new IllegalArgumentException("Guarantee not found: " + id));
    }

    public BankGuarantee findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Guarantee not found: " + id));
    }
}