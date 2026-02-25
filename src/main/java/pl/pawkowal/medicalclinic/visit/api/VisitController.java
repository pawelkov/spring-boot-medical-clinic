package pl.pawkowal.medicalclinic.visit.api;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import pl.pawkowal.medicalclinic.visit.application.VisitService;

import java.util.List;

@RestController
@RequestMapping("/v1/visits")
public class VisitController {

    private final VisitService visitService;

    private final VisitMapper mapper;

    public VisitController(VisitService visitService, VisitMapper mapper) {
        this.visitService = visitService;
        this.mapper = mapper;
    }

    @GetMapping
    public List<VisitDto> getAll() { return visitService.getAll().stream().map(mapper::toDto).toList(); }

    @GetMapping("/{id}")
    public VisitDto getById(@PathVariable Long id) { return mapper.toDto(visitService.getById(id)); }

    @PostMapping
    public VisitDto create(@Valid @RequestBody VisitDto dto) {
        return mapper.toDto(visitService.create(dto));
    }

    @PutMapping("/{id}")
    public VisitDto update(@PathVariable Long id, @Valid @RequestBody VisitDto dto) {
        return  mapper.toDto(visitService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { visitService.delete(id); }

}
