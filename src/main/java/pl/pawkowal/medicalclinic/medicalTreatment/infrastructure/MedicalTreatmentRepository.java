package pl.pawkowal.medicalclinic.medicalTreatment.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pawkowal.medicalclinic.medicalTreatment.domain.MedicalTreatment;

public interface MedicalTreatmentRepository extends JpaRepository<MedicalTreatment, Long> {
}
