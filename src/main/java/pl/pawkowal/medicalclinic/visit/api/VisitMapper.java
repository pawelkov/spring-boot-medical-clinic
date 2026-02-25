package pl.pawkowal.medicalclinic.visit.api;

import org.springframework.stereotype.Component;
import pl.pawkowal.medicalclinic.visit.domain.Visit;

@Component
public class VisitMapper {
    VisitDto toDto(Visit visit) {
        return new VisitDto(
                visit.getId(),
                visit.getPatient().getId(),
                visit.getDoctor().getId(),
                visit.getScheduledAt(),
                visit.getCost(),
                visit.getVisitStatus()
        );
    }
}
