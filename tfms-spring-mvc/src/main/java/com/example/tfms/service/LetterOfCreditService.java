package com.example.tfms.service;

import com.example.tfms.model.LetterOfCredit;
import com.example.tfms.model.enums.LcStatus;
import com.example.tfms.repository.LetterOfCreditRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LetterOfCreditService {

    private final LetterOfCreditRepository repository;

    public LetterOfCreditService(LetterOfCreditRepository repository) {
        this.repository = repository;
    }

    public LetterOfCredit create(LetterOfCredit lc) {
        lc.setStatus(LcStatus.OPEN);
        return repository.save(lc);
    }

    public LetterOfCredit amend(Long id, LetterOfCredit updated) {
        return repository.findById(id).map(existing -> {
            existing.setAmount(updated.getAmount());
            existing.setCurrency(updated.getCurrency());
            existing.setExpiryDate(updated.getExpiryDate());
            existing.setBeneficiaryName(updated.getBeneficiaryName());
            existing.setApplicantName(updated.getApplicantName());
            existing.setStatus(LcStatus.AMENDED);
            return repository.save(existing);
        }).orElseThrow(() -> new IllegalArgumentException("LC not found: " + id));
    }

    public LetterOfCredit close(Long id) {
        return repository.findById(id).map(existing -> {
            existing.setStatus(LcStatus.CLOSED);
            return repository.save(existing);
        }).orElseThrow(() -> new IllegalArgumentException("LC not found: " + id));
    }

    public List<LetterOfCredit> findAll() { return repository.findAll(); }

    public LetterOfCredit findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("LC not found: " + id));
    }
}