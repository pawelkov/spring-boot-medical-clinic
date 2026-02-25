package pl.pawkowal.medicalclinic.visit.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.pawkowal.medicalclinic.doctor.domain.Doctor;
import pl.pawkowal.medicalclinic.doctor.infrastructure.DoctorRepository;
import pl.pawkowal.medicalclinic.patient.domain.Patient;
import pl.pawkowal.medicalclinic.patient.infrastructure.PatientRepository;
import pl.pawkowal.medicalclinic.visit.api.VisitDto;
import pl.pawkowal.medicalclinic.visit.domain.Visit;
import pl.pawkowal.medicalclinic.visit.infrastructure.VisitRepository;

import java.util.List;

@Service
@Transactional
public class VisitService {

    private final VisitRepository visitRepository;

    private final PatientRepository patientRepository;

    private final DoctorRepository doctorRepository;

    public VisitService(VisitRepository visitRepository, PatientRepository patientRepository, DoctorRepository doctorRepository) {
        this.visitRepository = visitRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    public Visit create(VisitDto dto) {
        Patient patient = patientRepository.findById(dto.patientId())
                .orElseThrow(() -> new IllegalArgumentException("Patient not found: " + dto.patientId()));

        Doctor doctor = doctorRepository.findById(dto.doctorId())
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found: " + dto.doctorId()));

        Visit visit = new Visit(
                patient,
                doctor,
                dto.scheduledAt(),
                dto.cost(),
                dto.status()
        );

        return visitRepository.save(visit);
    }

    @Transactional(readOnly = true)
    public Visit getById(Long id) {
        return visitRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Visit not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<Visit> getAll() { return visitRepository.findAll(); }

    public Visit update(Long id, VisitDto dto) {
        Visit existing = visitRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Visit not found: " + id));

        Doctor doctor = doctorRepository.findById(dto.doctorId())
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found: " + dto.doctorId()));

        existing.update(
                doctor,
                dto.scheduledAt(),
                dto.cost(),
                dto.status()
        );

        return existing;
    }

    public void delete(Long id) {
        Visit visit = visitRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Visit not found: " + id));
        visitRepository.delete(visit);
    }
}
