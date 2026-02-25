package pl.pawkowal.medicalclinic.address.api;

import org.springframework.stereotype.Component;
import pl.pawkowal.medicalclinic.address.domain.Address;

@Component
public class AddressMapper {
    AddressDto toDto(Address address) {
        return new AddressDto(
                address.getId(),
                address.getCity(),
                address.getStreet(),
                address.getPostalCode(),
                address.getAddressLine()
        );
    }
}
