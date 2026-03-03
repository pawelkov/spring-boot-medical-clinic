package pl.pawkowal.medicalclinic.doctor.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.pawkowal.medicalclinic.common.exception.ResourceNotFoundException;
import pl.pawkowal.medicalclinic.doctor.domain.Doctor;
import pl.pawkowal.medicalclinic.doctor.infrastructure.DoctorRepository;
import pl.pawkowal.medicalclinic.address.domain.Address;
import pl.pawkowal.medicalclinic.address.infrastructure.AddressRepository;
import pl.pawkowal.medicalclinic.doctor.api.DoctorDto;

import java.util.List;

@Service
@Transactional
public class DoctorService {

    private final DoctorRepository doctorRepository;

    private final AddressRepository addressRepository;

    public DoctorService(DoctorRepository doctorRepository, AddressRepository addressRepository) {
        this.doctorRepository = doctorRepository;
        this.addressRepository = addressRepository;
    }

    public Doctor create(DoctorDto dto) {
        Address address = addressRepository.findById(dto.addressId())
                .orElseThrow(() -> new ResourceNotFoundException("Address not found: " + dto.addressId()));

        Doctor doctor = new Doctor(
                address,
                dto.firstName(),
                dto.lastName(),
                dto.phoneNumber(),
                dto.email()
        );

        return doctorRepository.save(doctor);
    }

    @Transactional(readOnly = true)
    public Doctor getById(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<Doctor> getAll() { return doctorRepository.findAll(); }

    public Doctor update(Long id, DoctorDto dto) {
        Doctor existing = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found: " + id));

        Address address = addressRepository.findById(dto.addressId())
                .orElseThrow(() -> new ResourceNotFoundException("Address not found: " + dto.addressId()));

        existing.update(
                address,
                dto.firstName(),
                dto.lastName(),
                dto.phoneNumber(),
                dto.email()
        );

        return existing;
    }

    public void delete(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found: " + id));
        doctorRepository.delete(doctor);
    }
}
