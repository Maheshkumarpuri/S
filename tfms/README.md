# Trade Finance Management System (TFMS)

Spring Boot MVC implementation of TFMS with modules for Letters of Credit, Bank Guarantees, Trade Documents, Risk Assessment, and Compliance.

## Run locally (H2)

```bash
mvn -q -f tfms/pom.xml spring-boot:run
```

App: http://localhost:8080
H2 Console: http://localhost:8080/h2-console (JDBC URL: `jdbc:h2:mem:tfms`)

## Run with MySQL profile

Create DB and user, then run with profile:

```bash
SPRING_CONFIG_NAME=application-mysql mvn -q -f tfms/pom.xml spring-boot:run
```

Update `src/main/resources/application-mysql.properties` accordingly.

## API Overview

- LC: `POST /api/lc`, `PUT /api/lc/{id}`, `POST /api/lc/{id}/close`, `GET /api/lc/{id}`, `GET /api/lc`
- Guarantees: `POST /api/guarantees/request`, `POST /api/guarantees/{id}/issue`, `GET /api/guarantees/{id}`, `GET /api/guarantees`
- Documents: `POST /api/documents/upload` (multipart: meta JSON, file), `GET /api/documents/{id}`, `PUT /api/documents/{id}`, `GET /api/documents`, `GET /api/documents/{id}/download`
- Risk: `POST /api/risk/analyze`, `GET /api/risk/{id}`, `GET /api/risk`
- Compliance: `GET /api/compliance/report?transactionRef=...`, `POST /api/compliance/submit`, `GET /api/compliance/{id}`, `GET /api/compliance`

## Notes

- Defaults to H2 in-memory for easy startup. Switch to MySQL with the provided properties file.
- File uploads are stored under `uploads/` relative to the project root.