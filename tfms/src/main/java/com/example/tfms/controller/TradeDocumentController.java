package com.example.tfms.controller;

import com.example.tfms.model.entity.TradeDocument;
import com.example.tfms.service.TradeDocumentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/documents")
public class TradeDocumentController {

    private final TradeDocumentService service;

    public TradeDocumentController(TradeDocumentService service) {
        this.service = service;
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<TradeDocument> upload(@Valid @RequestPart("meta") TradeDocument meta,
                                                @RequestPart("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(service.upload(meta, file));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TradeDocument> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TradeDocument> update(@PathVariable("id") Long id, @RequestBody TradeDocument updates) {
        return ResponseEntity.ok(service.update(id, updates));
    }

    @GetMapping
    public ResponseEntity<List<TradeDocument>> list() {
        return ResponseEntity.ok(service.listAll());
    }

    @GetMapping("/{id}/download")
    public ResponseEntity<byte[]> download(@PathVariable("id") Long id) throws IOException {
        byte[] data = service.downloadFile(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=doc_" + id)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(data);
    }
}