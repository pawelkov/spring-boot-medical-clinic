package pl.pawkowal.medicalclinic.medicalTreatment.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.pawkowal.medicalclinic.medicalTreatment.application.MedicalTreatmentService;

import java.util.List;

@RestController
@RequestMapping("/v1/medicalTreatments")
public class MedicalTreatmentController {

    private final MedicalTreatmentService medicalTreatmentService;

    private final MedicalTreatmentMapper mapper;

    public MedicalTreatmentController(MedicalTreatmentService medicalTreatmentService, MedicalTreatmentMapper mapper) {
        this.medicalTreatmentService = medicalTreatmentService;
        this.mapper = mapper;
    }

    @GetMapping
    public List<MedicalTreatmentDto> getAll() { return medicalTreatmentService.getAll().stream().map(mapper::toDto).toList(); }

    @GetMapping("/{id}")
    public MedicalTreatmentDto getById(@PathVariable Long id) { return mapper.toDto(medicalTreatmentService.getById(id)); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MedicalTreatmentDto create(@Valid @RequestBody MedicalTreatmentDto dto) {
        return mapper.toDto(medicalTreatmentService.create(dto));
    }

    @PutMapping("/{id}")
    public MedicalTreatmentDto update(@PathVariable Long id, @Valid @RequestBody MedicalTreatmentDto dto) {
        return  mapper.toDto(medicalTreatmentService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { medicalTreatmentService.delete(id); }

}
