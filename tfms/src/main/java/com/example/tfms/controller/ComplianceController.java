package com.example.tfms.controller;

import com.example.tfms.model.entity.Compliance;
import com.example.tfms.service.ComplianceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compliance")
public class ComplianceController {

    private final ComplianceService service;

    public ComplianceController(ComplianceService service) {
        this.service = service;
    }

    @GetMapping("/report")
    public ResponseEntity<Compliance> generateReport(@RequestParam("transactionRef") String transactionRef) {
        return ResponseEntity.ok(service.generateReport(transactionRef));
    }

    @PostMapping("/submit")
    public ResponseEntity<Compliance> submit(@RequestBody Compliance report) {
        return ResponseEntity.ok(service.submitReport(report));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Compliance> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<Compliance>> list() {
        return ResponseEntity.ok(service.listAll());
    }
}