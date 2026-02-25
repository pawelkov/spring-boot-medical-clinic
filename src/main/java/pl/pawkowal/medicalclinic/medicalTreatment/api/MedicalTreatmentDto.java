package pl.pawkowal.medicalclinic.medicalTreatment.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import pl.pawkowal.medicalclinic.medicalTreatment.domain.TreatmentType;

public record MedicalTreatmentDto(
        Long id,
        @NotNull Long visitId,
        @NotNull TreatmentType treatmentType,
        @NotBlank String description
) { }
