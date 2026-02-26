# Medical Clinic REST API

Backend REST application for managing patients, doctors, visits and medical treatments in a medical clinic.

## Tech Stack

- Java 17
- Spring Boot 3
- Spring Web (REST)
- Spring Data JPA (Hibernate)
- H2 (development) / MySQL
- Jakarta Validation
- JUnit 5 + Mockito (WebMvc tests)

---

## Project Structure

The application follows a layered architecture:

api            – controllers, DTOs and mappers  
application    – business logic (services)  
domain         – entities and enums  
infrastructure – repositories  
bootstrap      – development data seeder

Controllers handle HTTP requests.  
Services contain business logic.  
Repositories manage database access.

---

## Features

### Patients
- Create, update, delete patient
- Get patient by id
- Get all patients

### Doctors
- Create, update, delete doctor
- Get doctor by id
- Get all doctors

### Visits
- Schedule visit
- Get visit by id
- Get all visits
- Update visit
- Delete visit

### Medical Treatments
- Add treatment to visit
- Get treatments
- Delete treatment

Validation is handled using Jakarta Validation annotations.

Enums are used for:
- VisitStatus
- TreatmentType

---

## Development Profile

The application includes a `DevDataSeeder` component that inserts sample data when running with the `dev` profile.

Example:
```
--spring.profiles.active=dev
```

This allows quick local testing with pre-populated data.

---

## Testing

Basic WebMvc tests are implemented for `VisitController`.

Tests use:
- `@WebMvcTest`
- `MockMvc`
- `@MockBean`
- `Mockito`

---

## Running the Application
```
mvn spring-boot:run
```

Application runs on:

http://localhost:8080
