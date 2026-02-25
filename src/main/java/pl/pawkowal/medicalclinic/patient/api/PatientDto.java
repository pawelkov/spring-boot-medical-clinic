package pl.pawkowal.medicalclinic.patient.api;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PatientDto(
        Long id,
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotNull LocalDate birthday,
        @NotBlank String phoneNumber,
        @Email String email
) { }
