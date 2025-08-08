package com.example.tfms.repository;

import com.example.tfms.model.entity.Compliance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplianceRepository extends JpaRepository<Compliance, Long> {
}