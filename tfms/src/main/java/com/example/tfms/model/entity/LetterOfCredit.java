package com.example.tfms.model.entity;

import com.example.tfms.model.entity.enums.LetterOfCreditStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
public class LetterOfCredit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lcId;

    @NotBlank
    private String applicantName;

    @NotBlank
    private String beneficiaryName;

    @NotNull
    @DecimalMin(value = "0.00", inclusive = false)
    private BigDecimal amount;

    @NotBlank
    private String currency;

    @NotNull
    private LocalDate expiryDate;

    @Enumerated(EnumType.STRING)
    private LetterOfCreditStatus status = LetterOfCreditStatus.OPEN;

    private OffsetDateTime createdAt = OffsetDateTime.now();

    private OffsetDateTime updatedAt = OffsetDateTime.now();

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = OffsetDateTime.now();
    }

    // Getters and setters
    public Long getLcId() { return lcId; }
    public void setLcId(Long lcId) { this.lcId = lcId; }
    public String getApplicantName() { return applicantName; }
    public void setApplicantName(String applicantName) { this.applicantName = applicantName; }
    public String getBeneficiaryName() { return beneficiaryName; }
    public void setBeneficiaryName(String beneficiaryName) { this.beneficiaryName = beneficiaryName; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
    public LocalDate getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }
    public LetterOfCreditStatus getStatus() { return status; }
    public void setStatus(LetterOfCreditStatus status) { this.status = status; }
    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }
    public OffsetDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(OffsetDateTime updatedAt) { this.updatedAt = updatedAt; }
}