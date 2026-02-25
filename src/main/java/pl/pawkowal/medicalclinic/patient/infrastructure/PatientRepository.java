package pl.pawkowal.medicalclinic.patient.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pawkowal.medicalclinic.patient.domain.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
