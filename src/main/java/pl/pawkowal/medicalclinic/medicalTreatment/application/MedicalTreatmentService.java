package pl.pawkowal.medicalclinic.medicalTreatment.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.pawkowal.medicalclinic.common.exception.ResourceNotFoundException;
import pl.pawkowal.medicalclinic.medicalTreatment.api.MedicalTreatmentDto;
import pl.pawkowal.medicalclinic.medicalTreatment.domain.MedicalTreatment;
import pl.pawkowal.medicalclinic.medicalTreatment.infrastructure.MedicalTreatmentRepository;
import pl.pawkowal.medicalclinic.visit.domain.Visit;
import pl.pawkowal.medicalclinic.visit.infrastructure.VisitRepository;

import java.util.List;

@Service
@Transactional
public class MedicalTreatmentService {

    private final MedicalTreatmentRepository medicalTreatmentRepository;

    private final VisitRepository visitRepository;

    public MedicalTreatmentService(MedicalTreatmentRepository medicalTreatmentRepository, VisitRepository visitRepository) {
        this.medicalTreatmentRepository = medicalTreatmentRepository;
        this.visitRepository = visitRepository;
    }

    public MedicalTreatment create(MedicalTreatmentDto dto) {
        Visit visit = visitRepository.findById(dto.visitId())
                .orElseThrow(() -> new ResourceNotFoundException("Visit not found: " + dto.visitId()));

        MedicalTreatment medicalTreatment = new MedicalTreatment(
                visit,
                dto.treatmentType(),
                dto.description()
        );

        return medicalTreatmentRepository.save(medicalTreatment);
    }

    @Transactional(readOnly = true)
    public MedicalTreatment getById(Long id) {
        return medicalTreatmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medical Treatment not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<MedicalTreatment> getAll() { return medicalTreatmentRepository.findAll(); }

    public MedicalTreatment update(Long id, MedicalTreatmentDto dto) {
        MedicalTreatment existing = medicalTreatmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medical Treatment not found: " + id));

        existing.update(
                dto.treatmentType(),
                dto.description()
        );

        return existing;
    }

    public void delete(Long id) {
        MedicalTreatment medicalTreatment = medicalTreatmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medical Treatment not found: " + id));
        medicalTreatmentRepository.delete(medicalTreatment);
    }
}
