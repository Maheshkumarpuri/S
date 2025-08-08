package com.example.tfms.controller;

import com.example.tfms.model.BankGuarantee;
import com.example.tfms.service.BankGuaranteeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/guarantees")
public class BankGuaranteeController {

    private final BankGuaranteeService service;

    public BankGuaranteeController(BankGuaranteeService service) {
        this.service = service;
    }

    @PostMapping("/request")
    public ResponseEntity<BankGuarantee> request(@RequestBody BankGuarantee guarantee) {
        return ResponseEntity.ok(service.request(guarantee));
    }

    @PutMapping("/{id}/issue")
    public ResponseEntity<BankGuarantee> issue(@PathVariable Long id) {
        return ResponseEntity.ok(service.issue(id));
    }

    @GetMapping("/{id}/status")
    public ResponseEntity<String> status(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id).getStatus().name());
    }
}