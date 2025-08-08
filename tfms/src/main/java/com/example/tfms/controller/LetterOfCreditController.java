package com.example.tfms.controller;

import com.example.tfms.model.entity.LetterOfCredit;
import com.example.tfms.service.LetterOfCreditService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lc")
public class LetterOfCreditController {

    private final LetterOfCreditService service;

    public LetterOfCreditController(LetterOfCreditService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<LetterOfCredit> create(@Valid @RequestBody LetterOfCredit lc) {
        return ResponseEntity.ok(service.create(lc));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LetterOfCredit> amend(@PathVariable("id") Long id, @RequestBody LetterOfCredit lc) {
        return ResponseEntity.ok(service.amend(id, lc));
    }

    @PostMapping("/{id}/close")
    public ResponseEntity<LetterOfCredit> close(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.close(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LetterOfCredit> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<LetterOfCredit>> list() {
        return ResponseEntity.ok(service.listAll());
    }
}