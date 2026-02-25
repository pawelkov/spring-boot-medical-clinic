package pl.pawkowal.medicalclinic.doctor.api;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DoctorDto(
        Long id,
        @NotNull Long addressId,
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank String phoneNumber,
        @NotBlank @Email String email
) { }
