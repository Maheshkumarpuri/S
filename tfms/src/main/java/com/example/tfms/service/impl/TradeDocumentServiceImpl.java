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
    public TradeDocument upload(TradeDocument document, MultipartFile file) {
        try {
            if (file != null && !file.isEmpty()) {
                String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
                Path target = storagePath.resolve(System.currentTimeMillis() + "_" + originalFilename);
                Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
                document.setFilePath(target.toString());
            }
            return repository.save(document);
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload file: " + e.getMessage(), e);
        }
    }

    @Override
    public TradeDocument getById(Long documentId) {
        return repository.findById(documentId)
                .orElseThrow(() -> new ResourceNotFoundException("TradeDocument not found: " + documentId));
    }

    @Override
    public List<TradeDocument> listAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Long documentId) {
        TradeDocument document = getById(documentId);
        if (document.getFilePath() != null) {
            try {
                Files.deleteIfExists(Path.of(document.getFilePath()));
            } catch (IOException e) {
                // Log error but continue with database deletion
            }
        }
        repository.deleteById(documentId);
    }

    @Override
    public long count() {
        return repository.count();
    }

    // Helper method for file download (not in interface but useful)
    public byte[] downloadFile(Long documentId) throws IOException {
        TradeDocument doc = getById(documentId);
        if (doc.getFilePath() == null) {
            throw new ResourceNotFoundException("File not found for document: " + documentId);
        }
        return Files.readAllBytes(Path.of(doc.getFilePath()));
    }
}