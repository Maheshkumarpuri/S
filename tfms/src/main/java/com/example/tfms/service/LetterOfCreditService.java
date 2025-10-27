package com.example.tfms.service;

import com.example.tfms.model.entity.LetterOfCredit;

import java.util.List;

public interface LetterOfCreditService {
    LetterOfCredit create(LetterOfCredit lc);
    LetterOfCredit amend(Long lcId, LetterOfCredit lcUpdates);
    LetterOfCredit close(Long lcId);
    LetterOfCredit getById(Long lcId);
    List<LetterOfCredit> listAll();
    List<LetterOfCredit> findRecent(int limit);
    long count();
}