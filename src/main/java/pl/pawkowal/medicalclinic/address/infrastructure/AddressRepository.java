package pl.pawkowal.medicalclinic.address.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pawkowal.medicalclinic.address.domain.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
