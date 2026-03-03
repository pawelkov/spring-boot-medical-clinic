package pl.pawkowal.medicalclinic.doctor.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.pawkowal.medicalclinic.doctor.application.DoctorService;

import java.util.List;

@RestController
@RequestMapping("/v1/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    private final DoctorMapper mapper;

    public DoctorController(DoctorService doctorService, DoctorMapper mapper) {
        this.doctorService = doctorService;
        this.mapper = mapper;
    }

    @GetMapping
    public List<DoctorDto> getAll() { return doctorService.getAll().stream().map(mapper::toDto).toList(); }

    @GetMapping("/{id}")
    public DoctorDto getById(@PathVariable Long id) { return mapper.toDto(doctorService.getById(id)); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DoctorDto create(@Valid @RequestBody DoctorDto dto) {
        return mapper.toDto(doctorService.create(dto));
    }

    @PutMapping("/{id}")
    public DoctorDto update(@PathVariable Long id, @Valid @RequestBody DoctorDto dto) {
        return  mapper.toDto(doctorService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { doctorService.delete(id); }

}
