package com.example.tfms.repository;

import com.example.tfms.model.entity.LetterOfCredit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LetterOfCreditRepository extends JpaRepository<LetterOfCredit, Long> {
}