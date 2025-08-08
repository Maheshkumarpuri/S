package com.example.tfms.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class RiskAssessment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long riskId;

    @NotBlank
    private String transactionReference;

    @Lob
    private String riskFactorsJson;

    @NotNull
    private BigDecimal riskScore;

    @NotNull
    private LocalDate assessmentDate = LocalDate.now();

    // Getters and setters
    public Long getRiskId() { return riskId; }
    public void setRiskId(Long riskId) { this.riskId = riskId; }
    public String getTransactionReference() { return transactionReference; }
    public void setTransactionReference(String transactionReference) { this.transactionReference = transactionReference; }
    public String getRiskFactorsJson() { return riskFactorsJson; }
    public void setRiskFactorsJson(String riskFactorsJson) { this.riskFactorsJson = riskFactorsJson; }
    public BigDecimal getRiskScore() { return riskScore; }
    public void setRiskScore(BigDecimal riskScore) { this.riskScore = riskScore; }
    public LocalDate getAssessmentDate() { return assessmentDate; }
    public void setAssessmentDate(LocalDate assessmentDate) { this.assessmentDate = assessmentDate; }
}