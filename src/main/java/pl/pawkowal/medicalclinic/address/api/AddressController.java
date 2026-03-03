package pl.pawkowal.medicalclinic.address.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.pawkowal.medicalclinic.address.application.AddressService;

import java.util.List;

@RestController
@RequestMapping("/v1/address")
public class AddressController {

    private final AddressService addressService;

    private final AddressMapper mapper;

    public AddressController(AddressService addressService, AddressMapper mapper) {
        this.addressService = addressService;
        this.mapper = mapper;
    }

    @GetMapping
    public List<AddressDto> getAll() { return addressService.getAll().stream().map(mapper::toDto).toList(); }

    @GetMapping("/{id}")
    public AddressDto getById(@PathVariable Long id) { return mapper.toDto(addressService.getById(id)); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AddressDto create(@Valid @RequestBody AddressDto dto) {
        return mapper.toDto(addressService.create(dto));
    }

    @PutMapping("/{id}")
    public AddressDto update(@PathVariable Long id, @Valid @RequestBody AddressDto dto) {
        return  mapper.toDto(addressService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { addressService.delete(id); }

}
