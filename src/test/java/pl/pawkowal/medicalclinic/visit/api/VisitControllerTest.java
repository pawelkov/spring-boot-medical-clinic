package pl.pawkowal.medicalclinic.visit.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.pawkowal.medicalclinic.common.api.GlobalExceptionHandler;
import pl.pawkowal.medicalclinic.common.exception.ResourceNotFoundException;
import pl.pawkowal.medicalclinic.visit.application.VisitService;
import pl.pawkowal.medicalclinic.visit.domain.Visit;
import pl.pawkowal.medicalclinic.visit.domain.VisitStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Import(GlobalExceptionHandler.class)
@WebMvcTest(VisitController.class)
public class VisitControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VisitService visitService;

    @MockBean
    private VisitMapper visitMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateVisit() throws Exception {
        // given
        VisitDto request = new VisitDto(
                null,
                1L,
                1L,
                LocalDateTime.of(2026, 2, 10, 10, 0),
                new BigDecimal("250.00"),
                VisitStatus.SCHEDULED
        );

        Visit visitSaved = mock(Visit.class);

        VisitDto responseDto = new VisitDto(
                1L,
                1L,
                1L,
                LocalDateTime.of(2026, 2, 10, 10, 0),
                new BigDecimal("250.00"),
                VisitStatus.SCHEDULED
        );

        when(visitService.create(any())).thenReturn(visitSaved);
        when(visitMapper.toDto(visitSaved)).thenReturn(responseDto);

        // when + then
        mockMvc.perform(post("/v1/visits")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.patientId").value(1))
                .andExpect(jsonPath("$.doctorId").value(1))
                .andExpect(jsonPath("$.scheduledAt").value("2026-02-10T10:00:00"))
                .andExpect(jsonPath("$.cost").value(250.00))
                .andExpect(jsonPath("$.status").value("SCHEDULED"));
    }

    @Test
    void shouldReturnVisitById() throws Exception {
        // given
        Long visitId = 1L;

        Visit visit = mock(Visit.class);

        VisitDto responseDto = new VisitDto(
                1L,
                1L,
                1L,
                LocalDateTime.of(2026, 2, 10, 10, 0),
                new BigDecimal("250.00"),
                VisitStatus.SCHEDULED
        );

        when(visitService.getById(visitId)).thenReturn(visit);
        when(visitMapper.toDto(visit)).thenReturn(responseDto);

        // when + then
        mockMvc.perform(get("/v1/visits/{id}", visitId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.patientId").value(1))
                .andExpect(jsonPath("$.doctorId").value(1))
                .andExpect(jsonPath("$.status").value("SCHEDULED"));
    }

    @Test
    void shouldReturnAllVisits() throws Exception {
        // given
        Visit v1 = mock(Visit.class);
        Visit v2 = mock(Visit.class);

        VisitDto dto1 = new VisitDto(
                1L,
                1L,
                1L,
                LocalDateTime.of(2026, 2, 10, 10, 0),
                new BigDecimal("250.00"),
                VisitStatus.SCHEDULED
        );

        VisitDto dto2 = new VisitDto(
                2L,
                2L,
                1L,
                LocalDateTime.of(2026, 2, 11, 11, 0),
                new BigDecimal("180.00"),
                VisitStatus.COMPLETED
        );

        when(visitService.getAll()).thenReturn(List.of(v1, v2));
        when(visitMapper.toDto(v1)).thenReturn(dto1);
        when(visitMapper.toDto(v2)).thenReturn(dto2);

        // when + then
        mockMvc.perform(get("/v1/visits"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));
    }

    @Test
    void shouldFailWhenPatientIdIsNull() throws Exception {
        VisitDto request = new VisitDto(
                null,
                null,
                1L,
                LocalDateTime.of(2026, 2, 10, 10, 0),
                new BigDecimal("250.00"),
                VisitStatus.SCHEDULED
        );

        mockMvc.perform(post("/v1/visits")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value("Validation failed"))
                .andExpect(jsonPath("$.path").value("/v1/visits"))
                .andExpect(jsonPath("$.validationErrors.patientId").exists());
    }

    @Test
    void shouldFailWhenCostIsNegative() throws Exception {
        VisitDto request = new VisitDto(
                null,
                1L,
                1L,
                LocalDateTime.of(2026, 2, 10, 10, 0),
                new BigDecimal("-1.00"),
                VisitStatus.SCHEDULED
        );

        mockMvc.perform(post("/v1/visits")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value("Validation failed"))
                .andExpect(jsonPath("$.path").value("/v1/visits"))
                .andExpect(jsonPath("$.validationErrors.cost").exists());
    }

    @Test
    void shouldReturn404WhenVisitNotFound_onGetById() throws Exception {
        // given
        Long visitId = 999L;
        when(visitService.getById(visitId))
                .thenThrow(new ResourceNotFoundException("Visit not found: " + visitId));

        // when + then
        mockMvc.perform(get("/v1/visits/{id}", visitId))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("Visit not found: 999"))
                .andExpect(jsonPath("$.path").value("/v1/visits/999"));
    }

    @Test
    void shouldReturn404WhenPatientNotFound_onCreate() throws Exception {
        // given
        VisitDto request = new VisitDto(
                null,
                999L,
                1L,
                LocalDateTime.of(2026, 2, 10, 10, 0),
                new BigDecimal("250.00"),
                VisitStatus.SCHEDULED
        );

        when(visitService.create(any()))
                .thenThrow(new ResourceNotFoundException("Patient not found: 999"));

        // when + then
        mockMvc.perform(post("/v1/visits")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("Patient not found: 999"))
                .andExpect(jsonPath("$.path").value("/v1/visits"));
    }
}
