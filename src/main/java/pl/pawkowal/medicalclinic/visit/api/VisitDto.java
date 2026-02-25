package pl.pawkowal.medicalclinic.visit.api;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import pl.pawkowal.medicalclinic.visit.domain.VisitStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record VisitDto(
        Long id,
        @NotNull Long patientId,
        @NotNull Long doctorId,
        @NotNull LocalDateTime scheduledAt,
        @PositiveOrZero @NotNull BigDecimal cost,
        @NotNull VisitStatus status
) { }
