package com.example.tfms.controller;

import com.example.tfms.model.TradeDocument;
import com.example.tfms.service.TradeDocumentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/documents")
public class TradeDocumentController {

    private final TradeDocumentService service;

    public TradeDocumentController(TradeDocumentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TradeDocument> upload(@RequestBody TradeDocument document) {
        return ResponseEntity.ok(service.create(document));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TradeDocument> view(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TradeDocument> update(@PathVariable Long id, @RequestBody TradeDocument updated) {
        return ResponseEntity.ok(service.update(id, updated));
    }

    @GetMapping
    public ResponseEntity<List<TradeDocument>> list() {
        return ResponseEntity.ok(service.findAll());
    }
}