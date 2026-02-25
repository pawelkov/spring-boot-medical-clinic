package pl.pawkowal.medicalclinic.patient.api;

import org.springframework.stereotype.Component;
import pl.pawkowal.medicalclinic.patient.domain.Patient;

@Component
public class PatientMapper {
    PatientDto toDto(Patient patient) {
        return new PatientDto(
                patient.getId(),
                patient.getFirstName(),
                patient.getLastName(),
                patient.getBirthday(),
                patient.getPhoneNumber(),
                patient.getEmail()
        );
    }
}
