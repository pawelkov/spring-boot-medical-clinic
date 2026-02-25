package pl.pawkowal.medicalclinic.doctor.api;

import org.springframework.stereotype.Component;
import pl.pawkowal.medicalclinic.doctor.domain.Doctor;

@Component
public class DoctorMapper {
    DoctorDto toDto(Doctor doctor) {
        return new DoctorDto(
                doctor.getId(),
                doctor.getAddress().getId(),
                doctor.getFirstName(),
                doctor.getLastName(),
                doctor.getPhoneNumber(),
                doctor.getEmail()
        );
    }
}
