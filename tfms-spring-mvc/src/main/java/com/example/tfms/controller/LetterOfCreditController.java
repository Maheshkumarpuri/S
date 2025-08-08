package com.example.tfms.controller;

import com.example.tfms.model.LetterOfCredit;
import com.example.tfms.service.LetterOfCreditService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lcs")
public class LetterOfCreditController {

    private final LetterOfCreditService service;

    public LetterOfCreditController(LetterOfCreditService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<LetterOfCredit> create(@RequestBody LetterOfCredit lc) {
        return ResponseEntity.ok(service.create(lc));
    }

    @PutMapping("/{id}/amend")
    public ResponseEntity<LetterOfCredit> amend(@PathVariable Long id, @RequestBody LetterOfCredit lc) {
        return ResponseEntity.ok(service.amend(id, lc));
    }

    @PutMapping("/{id}/close")
    public ResponseEntity<LetterOfCredit> close(@PathVariable Long id) {
        return ResponseEntity.ok(service.close(id));
    }

    @GetMapping
    public ResponseEntity<List<LetterOfCredit>> list() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LetterOfCredit> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }
}