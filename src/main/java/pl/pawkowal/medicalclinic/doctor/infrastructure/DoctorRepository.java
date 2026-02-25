package pl.pawkowal.medicalclinic.doctor.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pawkowal.medicalclinic.doctor.domain.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
