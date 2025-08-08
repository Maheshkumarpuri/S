package com.example.tfms.model.entity;

import com.example.tfms.model.entity.enums.BankGuaranteeStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
public class BankGuarantee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long guaranteeId;

    @NotBlank
    private String applicantName;

    @NotBlank
    private String beneficiaryName;

    @NotNull
    @DecimalMin(value = "0.00", inclusive = false)
    private BigDecimal guaranteeAmount;

    @NotBlank
    private String currency;

    @NotNull
    private LocalDate validityPeriod;

    @Enumerated(EnumType.STRING)
    private BankGuaranteeStatus status = BankGuaranteeStatus.PENDING;

    private OffsetDateTime createdAt = OffsetDateTime.now();

    // Getters and setters
    public Long getGuaranteeId() { return guaranteeId; }
    public void setGuaranteeId(Long guaranteeId) { this.guaranteeId = guaranteeId; }
    public String getApplicantName() { return applicantName; }
    public void setApplicantName(String applicantName) { this.applicantName = applicantName; }
    public String getBeneficiaryName() { return beneficiaryName; }
    public void setBeneficiaryName(String beneficiaryName) { this.beneficiaryName = beneficiaryName; }
    public BigDecimal getGuaranteeAmount() { return guaranteeAmount; }
    public void setGuaranteeAmount(BigDecimal guaranteeAmount) { this.guaranteeAmount = guaranteeAmount; }
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
    public LocalDate getValidityPeriod() { return validityPeriod; }
    public void setValidityPeriod(LocalDate validityPeriod) { this.validityPeriod = validityPeriod; }
    public BankGuaranteeStatus getStatus() { return status; }
    public void setStatus(BankGuaranteeStatus status) { this.status = status; }
    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }
}