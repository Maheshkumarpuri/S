package com.example.tfms.controller;

import com.example.tfms.model.entity.BankGuarantee;
import com.example.tfms.service.BankGuaranteeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guarantees")
public class BankGuaranteeController {

    private final BankGuaranteeService service;

    public BankGuaranteeController(BankGuaranteeService service) {
        this.service = service;
    }

    @PostMapping("/request")
    public ResponseEntity<BankGuarantee> request(@Valid @RequestBody BankGuarantee guarantee) {
        return ResponseEntity.ok(service.request(guarantee));
    }

    @PostMapping("/{id}/issue")
    public ResponseEntity<BankGuarantee> issue(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.issue(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankGuarantee> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<BankGuarantee>> list() {
        return ResponseEntity.ok(service.listAll());
    }
}