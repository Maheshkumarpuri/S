package com.example.tfms.service;

import com.example.tfms.model.TradeDocument;
import com.example.tfms.repository.TradeDocumentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeDocumentService {
    private final TradeDocumentRepository repository;

    public TradeDocumentService(TradeDocumentRepository repository) {
        this.repository = repository;
    }

    public TradeDocument create(TradeDocument doc) {
        return repository.save(doc);
    }

    public TradeDocument update(Long id, TradeDocument updated) {
        return repository.findById(id).map(existing -> {
            existing.setDocumentType(updated.getDocumentType());
            existing.setReferenceNumber(updated.getReferenceNumber());
            existing.setUploadedBy(updated.getUploadedBy());
            existing.setUploadDate(updated.getUploadDate());
            existing.setStatus(updated.getStatus());
            return repository.save(existing);
        }).orElseThrow(() -> new IllegalArgumentException("Document not found: " + id));
    }

    public TradeDocument findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Document not found: " + id));
    }

    public List<TradeDocument> findAll() { return repository.findAll(); }
}