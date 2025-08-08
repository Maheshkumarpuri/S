-- Trade Finance Management System Database Schema
-- Created for MySQL/H2 compatibility

-- Letter of Credit Table
CREATE TABLE IF NOT EXISTS letter_of_credit (
    lc_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    applicant_name VARCHAR(100) NOT NULL,
    beneficiary_name VARCHAR(100) NOT NULL,
    amount DECIMAL(15, 2) NOT NULL,
    currency VARCHAR(10) NOT NULL,
    expiry_date DATE NOT NULL,
    status VARCHAR(20) DEFAULT 'OPEN',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Bank Guarantee Table
CREATE TABLE IF NOT EXISTS bank_guarantee (
    guarantee_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    applicant_name VARCHAR(100) NOT NULL,
    beneficiary_name VARCHAR(100) NOT NULL,
    guarantee_amount DECIMAL(15, 2) NOT NULL,
    currency VARCHAR(10) NOT NULL,
    validity_period DATE NOT NULL,
    status VARCHAR(20) DEFAULT 'PENDING',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Trade Document Table
CREATE TABLE IF NOT EXISTS trade_document (
    document_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    document_type VARCHAR(50) NOT NULL,
    reference_number VARCHAR(50) NOT NULL,
    uploaded_by VARCHAR(50) NOT NULL,
    upload_date DATE NOT NULL,
    file_path VARCHAR(255),
    status VARCHAR(20) DEFAULT 'ACTIVE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Risk Assessment Table
CREATE TABLE IF NOT EXISTS risk_assessment (
    risk_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    transaction_reference VARCHAR(50) NOT NULL,
    risk_factors JSON,
    risk_score DECIMAL(5, 2),
    assessment_date DATE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Compliance Table
CREATE TABLE IF NOT EXISTS compliance (
    compliance_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    transaction_reference VARCHAR(50) NOT NULL,
    compliance_status VARCHAR(20) DEFAULT 'COMPLIANT',
    remarks VARCHAR(255),
    report_date DATE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Insert sample data for testing
INSERT INTO letter_of_credit (applicant_name, beneficiary_name, amount, currency, expiry_date, status) 
VALUES 
    ('ABC Corp', 'XYZ Ltd', 100000.00, 'USD', '2024-12-31', 'OPEN'),
    ('Global Trade Inc', 'International Suppliers', 250000.00, 'EUR', '2024-11-30', 'AMENDED');

INSERT INTO bank_guarantee (applicant_name, beneficiary_name, guarantee_amount, currency, validity_period, status)
VALUES 
    ('Construction Co', 'City Council', 500000.00, 'USD', '2025-06-30', 'ISSUED'),
    ('Tech Solutions', 'Government Agency', 150000.00, 'USD', '2024-12-15', 'PENDING');

INSERT INTO trade_document (document_type, reference_number, uploaded_by, upload_date, status)
VALUES 
    ('Invoice', 'INV-2024-001', 'admin', CURRENT_DATE, 'ACTIVE'),
    ('Bill of Lading', 'BOL-2024-001', 'user1', CURRENT_DATE, 'ACTIVE');

INSERT INTO risk_assessment (transaction_reference, risk_factors, risk_score, assessment_date)
VALUES 
    ('LC-001', '{"country_risk": "low", "credit_risk": "medium"}', 3.5, CURRENT_DATE),
    ('BG-001', '{"country_risk": "high", "credit_risk": "low"}', 4.2, CURRENT_DATE);

INSERT INTO compliance (transaction_reference, compliance_status, remarks, report_date)
VALUES 
    ('LC-001', 'COMPLIANT', 'All documentation complete', CURRENT_DATE),
    ('BG-001', 'NON_COMPLIANT', 'Missing KYC documents', CURRENT_DATE);