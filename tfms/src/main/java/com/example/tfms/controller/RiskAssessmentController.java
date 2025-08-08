package com.example.tfms.controller;

import com.example.tfms.model.entity.RiskAssessment;
import com.example.tfms.service.RiskAssessmentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/risk")
public class RiskAssessmentController {

    private final RiskAssessmentService service;

    public RiskAssessmentController(RiskAssessmentService service) {
        this.service = service;
    }

    @PostMapping("/analyze")
    public ResponseEntity<RiskAssessment> analyze(@Valid @RequestBody RiskAssessment request) {
        return ResponseEntity.ok(service.analyze(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RiskAssessment> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<RiskAssessment>> list() {
        return ResponseEntity.ok(service.listAll());
    }
}