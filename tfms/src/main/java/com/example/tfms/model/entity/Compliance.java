package com.example.tfms.model.entity;

import com.example.tfms.model.entity.enums.ComplianceStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
public class Compliance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long complianceId;

    @NotBlank
    private String transactionReference;

    @Enumerated(EnumType.STRING)
    private ComplianceStatus complianceStatus;

    private String remarks;

    @NotNull
    private LocalDate reportDate = LocalDate.now();

    // Getters and setters
    public Long getComplianceId() { return complianceId; }
    public void setComplianceId(Long complianceId) { this.complianceId = complianceId; }
    public String getTransactionReference() { return transactionReference; }
    public void setTransactionReference(String transactionReference) { this.transactionReference = transactionReference; }
    public ComplianceStatus getComplianceStatus() { return complianceStatus; }
    public void setComplianceStatus(ComplianceStatus complianceStatus) { this.complianceStatus = complianceStatus; }
    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
    public LocalDate getReportDate() { return reportDate; }
    public void setReportDate(LocalDate reportDate) { this.reportDate = reportDate; }
}