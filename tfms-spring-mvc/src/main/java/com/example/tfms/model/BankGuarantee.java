package com.example.tfms.model;

import com.example.tfms.model.enums.GuaranteeStatus;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class BankGuarantee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String applicantName;
    private String beneficiaryName;

    private BigDecimal guaranteeAmount;

    private String currency;

    private LocalDate validityPeriod;

    @Enumerated(EnumType.STRING)
    private GuaranteeStatus status = GuaranteeStatus.PENDING;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

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

    public GuaranteeStatus getStatus() { return status; }
    public void setStatus(GuaranteeStatus status) { this.status = status; }
}