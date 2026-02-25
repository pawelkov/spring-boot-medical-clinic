package pl.pawkowal.medicalclinic.address.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.pawkowal.medicalclinic.address.api.AddressDto;
import pl.pawkowal.medicalclinic.address.domain.Address;
import pl.pawkowal.medicalclinic.address.infrastructure.AddressRepository;

import java.util.List;

@Service
@Transactional
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address create(AddressDto dto) {
        Address address = new Address(
                dto.city(),
                dto.street(),
                dto.postalCode(),
                dto.addressLine()
        );

        return addressRepository.save(address);
    }

    @Transactional(readOnly = true)
    public Address getById(Long id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Address not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<Address> getAll() { return addressRepository.findAll(); }

    public Address update(Long id, AddressDto dto) {
        Address existing = addressRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Address not found: " + id));

        existing.update(
                dto.city(),
                dto.street(),
                dto.postalCode(),
                dto.addressLine()
        );

        return existing;
    }

    public void delete(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Address not found: " + id));
        addressRepository.delete(address);
    }
}
