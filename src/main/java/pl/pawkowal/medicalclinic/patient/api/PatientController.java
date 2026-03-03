package pl.pawkowal.medicalclinic.patient.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.pawkowal.medicalclinic.patient.application.PatientService;

import java.util.List;

@RestController
@RequestMapping("/v1/patients")
public class PatientController {

    private final PatientService patientService;

    private final PatientMapper mapper;

    public PatientController(PatientService patientService, PatientMapper mapper) {
        this.patientService = patientService;
        this.mapper = mapper;
    }

    @GetMapping
    public List<PatientDto> getAll() { return patientService.getAll().stream().map(mapper::toDto).toList(); }

    @GetMapping("/{id}")
    public PatientDto getById(@PathVariable Long id) { return mapper.toDto(patientService.getById(id)); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PatientDto create(@Valid @RequestBody PatientDto dto) {
        return mapper.toDto(patientService.create(dto));
    }

    @PutMapping("/{id}")
    public PatientDto update(@PathVariable Long id, @Valid @RequestBody PatientDto dto) {
        return  mapper.toDto(patientService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { patientService.delete(id); }

}
