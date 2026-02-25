package pl.pawkowal.medicalclinic.visit.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pawkowal.medicalclinic.visit.domain.Visit;

public interface VisitRepository extends JpaRepository<Visit, Long> {
}
