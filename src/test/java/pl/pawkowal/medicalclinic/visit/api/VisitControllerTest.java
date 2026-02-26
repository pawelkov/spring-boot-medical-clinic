package pl.pawkowal.medicalclinic.visit.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.pawkowal.medicalclinic.visit.application.VisitService;
import pl.pawkowal.medicalclinic.visit.domain.Visit;
import pl.pawkowal.medicalclinic.visit.domain.VisitStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.patientId").value(1))
                .andExpect(jsonPath("$.doctorId").value(1))
                .andExpect(jsonPath("$.scheduledAt").value("2026-02-10T10:00:00"))
                .andExpect(jsonPath("$.cost").value(250.00))
                .andExpect(jsonPath("$.status").value("SCHEDULED"));
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
                .andExpect(status().isBadRequest());
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
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.patientId").value(1))
                .andExpect(jsonPath("$.doctorId").value(1))
                .andExpect(jsonPath("$.status").value("SCHEDULED"));
    }
}
