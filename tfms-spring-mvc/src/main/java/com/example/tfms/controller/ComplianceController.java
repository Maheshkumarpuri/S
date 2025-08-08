package com.example.tfms.controller;

import com.example.tfms.model.Compliance;
import com.example.tfms.service.ComplianceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/compliance")
public class ComplianceController {

    private final ComplianceService service;

    public ComplianceController(ComplianceService service) {
        this.service = service;
    }

    @PostMapping("/report")
    public ResponseEntity<Compliance> generate(@RequestBody Map<String, Object> payload) {
        String txRef = String.valueOf(payload.getOrDefault("transactionReference", "UNKNOWN"));
        boolean sanctionsHit = Boolean.parseBoolean(String.valueOf(payload.getOrDefault("sanctionsHit", false)));
        boolean documentsComplete = Boolean.parseBoolean(String.valueOf(payload.getOrDefault("documentsComplete", true)));
        return ResponseEntity.ok(service.generateReport(txRef, sanctionsHit, documentsComplete));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Compliance> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }
}