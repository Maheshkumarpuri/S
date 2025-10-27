package com.example.tfms.repository;

import com.example.tfms.model.entity.Compliance;
import com.example.tfms.model.entity.enums.ComplianceStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComplianceRepository extends JpaRepository<Compliance, Long> {
    List<Compliance> findByComplianceStatus(ComplianceStatus status);
}