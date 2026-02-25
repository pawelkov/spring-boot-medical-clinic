package pl.pawkowal.medicalclinic.address.api;

import jakarta.validation.constraints.NotBlank;

public record AddressDto(
        Long id,
        @NotBlank String city,
        @NotBlank String street,
        @NotBlank String postalCode,
        @NotBlank String addressLine
) { }
