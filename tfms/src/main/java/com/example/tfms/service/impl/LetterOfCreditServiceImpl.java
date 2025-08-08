package com.example.tfms.service.impl;

import com.example.tfms.exception.ResourceNotFoundException;
import com.example.tfms.model.entity.LetterOfCredit;
import com.example.tfms.model.entity.enums.LetterOfCreditStatus;
import com.example.tfms.repository.LetterOfCreditRepository;
import com.example.tfms.service.LetterOfCreditService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LetterOfCreditServiceImpl implements LetterOfCreditService {

    private final LetterOfCreditRepository repository;

    public LetterOfCreditServiceImpl(LetterOfCreditRepository repository) {
        this.repository = repository;
    }

    @Override
    public LetterOfCredit create(LetterOfCredit lc) {
        lc.setStatus(LetterOfCreditStatus.OPEN);
        return repository.save(lc);
    }

    @Override
    public LetterOfCredit amend(Long lcId, LetterOfCredit lcUpdates) {
        LetterOfCredit existing = repository.findById(lcId)
                .orElseThrow(() -> new ResourceNotFoundException("LetterOfCredit not found: " + lcId));
        if (lcUpdates.getApplicantName() != null) existing.setApplicantName(lcUpdates.getApplicantName());
        if (lcUpdates.getBeneficiaryName() != null) existing.setBeneficiaryName(lcUpdates.getBeneficiaryName());
        if (lcUpdates.getAmount() != null) existing.setAmount(lcUpdates.getAmount());
        if (lcUpdates.getCurrency() != null) existing.setCurrency(lcUpdates.getCurrency());
        if (lcUpdates.getExpiryDate() != null) existing.setExpiryDate(lcUpdates.getExpiryDate());
        existing.setStatus(LetterOfCreditStatus.AMENDED);
        return repository.save(existing);
    }

    @Override
    public LetterOfCredit close(Long lcId) {
        LetterOfCredit existing = repository.findById(lcId)
                .orElseThrow(() -> new ResourceNotFoundException("LetterOfCredit not found: " + lcId));
        existing.setStatus(LetterOfCreditStatus.CLOSED);
        return repository.save(existing);
    }

    @Override
    public LetterOfCredit getById(Long lcId) {
        return repository.findById(lcId)
                .orElseThrow(() -> new ResourceNotFoundException("LetterOfCredit not found: " + lcId));
    }

    @Override
    public List<LetterOfCredit> listAll() {
        return repository.findAll();
    }
}