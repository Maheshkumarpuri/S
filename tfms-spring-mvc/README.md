# TFMS - Trade Finance Management System (Spring MVC)

Spring Boot application implementing core modules: Letters of Credit, Bank Guarantees, Trade Documents, Risk Assessment, and Compliance.

## Run locally

- Prerequisites: Java 17+, Maven 3.9+
- Start:

```bash
cd tfms-spring-mvc
mvn spring-boot:run
```

App runs at http://localhost:8080
Swagger UI: http://localhost:8080/swagger-ui/index.html
H2 console: http://localhost:8080/h2-console (JDBC URL: `jdbc:h2:mem:tfms`)

## Run with Docker (no local Java/Maven needed)

```bash
# From repo root
cd tfms-spring-mvc
# Build image
docker build -t tfms-spring .
# Run container
docker run --rm -p 8080:8080 tfms-spring
```

## Switch to MySQL

Edit `src/main/resources/application.properties` and set the MySQL datasource properties. Ensure a database named `tfms` exists.

## Sample requests

- Create LC
```bash
curl -X POST http://localhost:8080/api/lcs \
  -H 'Content-Type: application/json' \
  -d '{
    "applicantName":"ABC Corp",
    "beneficiaryName":"XYZ Ltd",
    "amount":100000,
    "currency":"USD",
    "expiryDate":"2025-12-31"
  }'
```

- Amend LC
```bash
curl -X PUT http://localhost:8080/api/lcs/1/amend \
  -H 'Content-Type: application/json' \
  -d '{
    "amount":120000,
    "currency":"USD",
    "expiryDate":"2026-01-31",
    "applicantName":"ABC Corp",
    "beneficiaryName":"XYZ Ltd"
  }'
```

- Close LC
```bash
curl -X PUT http://localhost:8080/api/lcs/1/close
```

- Request Guarantee
```bash
curl -X POST http://localhost:8080/api/guarantees/request \
  -H 'Content-Type: application/json' \
  -d '{
    "applicantName":"ABC Corp",
    "beneficiaryName":"XYZ Ltd",
    "guaranteeAmount":50000,
    "currency":"EUR",
    "validityPeriod":"2026-06-30"
  }'
```

- Issue Guarantee
```bash
curl -X PUT http://localhost:8080/api/guarantees/1/issue
```

- Upload Trade Document
```bash
curl -X POST http://localhost:8080/api/documents \
  -H 'Content-Type: application/json' \
  -d '{
    "documentType":"Invoice",
    "referenceNumber":"INV-001",
    "uploadedBy":"john.doe",
    "uploadDate":"2025-01-01",
    "status":"ACTIVE"
  }'
```

- Analyze Risk
```bash
curl -X POST http://localhost:8080/api/risks/analyze \
  -H 'Content-Type: application/json' \
  -d '{
    "transactionReference":"TX-1001",
    "riskFactors": { "countryRisk": 6, "amount": 750000, "docsCompleteness": 0.8 }
  }'
```

- Generate Compliance Report
```bash
curl -X POST http://localhost:8080/api/compliance/report \
  -H 'Content-Type: application/json' \
  -d '{
    "transactionReference":"TX-1001",
    "sanctionsHit": false,
    "documentsComplete": true
  }'
```