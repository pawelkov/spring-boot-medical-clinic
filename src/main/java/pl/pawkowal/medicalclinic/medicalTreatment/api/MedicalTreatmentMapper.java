package pl.pawkowal.medicalclinic.medicalTreatment.api;

import org.springframework.stereotype.Component;
import pl.pawkowal.medicalclinic.medicalTreatment.domain.MedicalTreatment;

@Component
public class MedicalTreatmentMapper {
    MedicalTreatmentDto toDto(MedicalTreatment medicalTreatment) {
        return new MedicalTreatmentDto(
                medicalTreatment.getId(),
                medicalTreatment.getVisit().getId(),
                medicalTreatment.getTreatmentType(),
                medicalTreatment.getDescription()
        );
    }
}
