package com.example.tfms.service;

import com.example.tfms.model.entity.BankGuarantee;

import java.util.List;

public interface BankGuaranteeService {
    BankGuarantee request(BankGuarantee guarantee);
    BankGuarantee issue(Long guaranteeId);
    BankGuarantee getById(Long guaranteeId);
    List<BankGuarantee> listAll();
}