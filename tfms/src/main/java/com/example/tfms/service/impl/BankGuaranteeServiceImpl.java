package com.example.tfms.service.impl;

import com.example.tfms.exception.ResourceNotFoundException;
import com.example.tfms.model.entity.BankGuarantee;
import com.example.tfms.model.entity.enums.BankGuaranteeStatus;
import com.example.tfms.repository.BankGuaranteeRepository;
import com.example.tfms.service.BankGuaranteeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankGuaranteeServiceImpl implements BankGuaranteeService {

    private final BankGuaranteeRepository repository;

    public BankGuaranteeServiceImpl(BankGuaranteeRepository repository) {
        this.repository = repository;
    }

    @Override
    public BankGuarantee request(BankGuarantee guarantee) {
        guarantee.setStatus(BankGuaranteeStatus.PENDING);
        return repository.save(guarantee);
    }

    @Override
    public BankGuarantee issue(Long guaranteeId) {
        BankGuarantee existing = repository.findById(guaranteeId)
                .orElseThrow(() -> new ResourceNotFoundException("BankGuarantee not found: " + guaranteeId));
        existing.setStatus(BankGuaranteeStatus.ISSUED);
        return repository.save(existing);
    }

    @Override
    public BankGuarantee getById(Long guaranteeId) {
        return repository.findById(guaranteeId)
                .orElseThrow(() -> new ResourceNotFoundException("BankGuarantee not found: " + guaranteeId));
    }

    @Override
    public List<BankGuarantee> listAll() {
        return repository.findAll();
    }

    @Override
    public long count() {
        return repository.count();
    }
}