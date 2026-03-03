package pl.pawkowal.medicalclinic.patient.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.pawkowal.medicalclinic.common.exception.ResourceNotFoundException;
import pl.pawkowal.medicalclinic.patient.api.PatientDto;
import pl.pawkowal.medicalclinic.patient.domain.Patient;
import pl.pawkowal.medicalclinic.patient.infrastructure.PatientRepository;

import java.util.List;

@Service
@Transactional
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient create(PatientDto dto) {
        Patient patient = new Patient(
                dto.firstName(),
                dto.lastName(),
                dto.birthday(),
                dto.phoneNumber(),
                dto.email()
        );

        return patientRepository.save(patient);
    }

    @Transactional(readOnly = true)
    public Patient getById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<Patient> getAll() { return patientRepository.findAll(); }

    public Patient update(Long id, PatientDto dto) {
        Patient existing = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found: " + id));

        existing.update(
                dto.firstName(),
                dto.lastName(),
                dto.birthday(),
                dto.phoneNumber(),
                dto.email()
        );

        return existing;
    }

    public void delete(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found: " + id));
        patientRepository.delete(patient);
    }
}
