package com.example.tfms.model.entity;

import com.example.tfms.model.entity.enums.TradeDocumentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
public class TradeDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long documentId;

    @NotBlank
    private String documentType;

    @NotBlank
    private String referenceNumber;

    @NotBlank
    private String uploadedBy;

    @NotNull
    private LocalDate uploadDate = LocalDate.now();

    @Enumerated(EnumType.STRING)
    private TradeDocumentStatus status = TradeDocumentStatus.ACTIVE;

    private String filePath;

    // Getters and setters
    public Long getDocumentId() { return documentId; }
    public void setDocumentId(Long documentId) { this.documentId = documentId; }
    public String getDocumentType() { return documentType; }
    public void setDocumentType(String documentType) { this.documentType = documentType; }
    public String getReferenceNumber() { return referenceNumber; }
    public void setReferenceNumber(String referenceNumber) { this.referenceNumber = referenceNumber; }
    public String getUploadedBy() { return uploadedBy; }
    public void setUploadedBy(String uploadedBy) { this.uploadedBy = uploadedBy; }
    public LocalDate getUploadDate() { return uploadDate; }
    public void setUploadDate(LocalDate uploadDate) { this.uploadDate = uploadDate; }
    public TradeDocumentStatus getStatus() { return status; }
    public void setStatus(TradeDocumentStatus status) { this.status = status; }
    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }
}