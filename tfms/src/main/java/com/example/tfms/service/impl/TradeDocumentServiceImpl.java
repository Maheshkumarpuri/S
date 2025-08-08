package com.example.tfms.service.impl;

import com.example.tfms.exception.ResourceNotFoundException;
import com.example.tfms.model.entity.TradeDocument;
import com.example.tfms.repository.TradeDocumentRepository;
import com.example.tfms.service.TradeDocumentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class TradeDocumentServiceImpl implements TradeDocumentService {

    private final TradeDocumentRepository repository;
    private final Path storagePath;

    public TradeDocumentServiceImpl(TradeDocumentRepository repository, @Value("${tfms.storage.location}") String storageLocation) throws IOException {
        this.repository = repository;
        this.storagePath = Paths.get(storageLocation).toAbsolutePath().normalize();
        Files.createDirectories(this.storagePath);
    }

    @Override
    public TradeDocument upload(TradeDocument meta, MultipartFile file) throws IOException {
        String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
        Path target = storagePath.resolve(System.currentTimeMillis() + "_" + originalFilename);
        Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
        meta.setFilePath(target.toString());
        return repository.save(meta);
    }

    @Override
    public TradeDocument getById(Long documentId) {
        return repository.findById(documentId)
                .orElseThrow(() -> new ResourceNotFoundException("TradeDocument not found: " + documentId));
    }

    @Override
    public TradeDocument update(Long documentId, TradeDocument updates) {
        TradeDocument existing = getById(documentId);
        if (updates.getDocumentType() != null) existing.setDocumentType(updates.getDocumentType());
        if (updates.getReferenceNumber() != null) existing.setReferenceNumber(updates.getReferenceNumber());
        if (updates.getUploadedBy() != null) existing.setUploadedBy(updates.getUploadedBy());
        if (updates.getStatus() != null) existing.setStatus(updates.getStatus());
        return repository.save(existing);
    }

    @Override
    public List<TradeDocument> listAll() {
        return repository.findAll();
    }

    @Override
    public byte[] downloadFile(Long documentId) throws IOException {
        TradeDocument doc = getById(documentId);
        if (doc.getFilePath() == null) {
            throw new ResourceNotFoundException("File not found for document: " + documentId);
        }
        return Files.readAllBytes(Path.of(doc.getFilePath()));
    }
}