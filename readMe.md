# Fraud Detection System
A fraud detection system built with Spring Boot and JUnit 5. It detects fraudulent transactions based on configurable rules, manages blacklisted users, and uses an in-memory H2 database.

## Features
- Fraud Detection Engine: Detects fraudulent transactions based on rules (e.g., blacklisted users, country of origin).
- Blacklisted User Management: Add, remove, and check blacklisted users.
- REST API: Exposes endpoints for fraud detection and blacklisting.
- Swagger UI: API documentation for easy testing.

## Technologies
- Spring Boot (Java)
- JUnit 5 (Unit Testing)
- H2 Database (In-memory database)
- Swagger/OpenAPI (API Documentation)

Every request must send ``X-User-Id`` header