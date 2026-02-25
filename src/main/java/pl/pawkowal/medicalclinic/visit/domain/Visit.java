package pl.pawkowal.medicalclinic.visit.domain;

import jakarta.persistence.*;
import pl.pawkowal.medicalclinic.doctor.domain.Doctor;
import pl.pawkowal.medicalclinic.patient.domain.Patient;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "visits")
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch =  FetchType.LAZY, optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @Column(nullable = false)
    private LocalDateTime scheduledAt;

    @Column(nullable = false)
    private BigDecimal cost;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VisitStatus status;

    protected Visit() {}

    public Visit(Patient patient, Doctor doctor, LocalDateTime scheduledAt, BigDecimal cost, VisitStatus status) {
        this.patient = patient;
        this.doctor = doctor;
        this.scheduledAt = scheduledAt;
        this.cost = cost;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public LocalDateTime getScheduledAt() {
        return scheduledAt;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public VisitStatus getVisitStatus() {
        return status;
    }

    public void update(Doctor doctor, LocalDateTime scheduledAt, BigDecimal cost, VisitStatus status) {
        this.doctor = doctor;
        this.scheduledAt = scheduledAt;
        this.cost = cost;
        this.status = status;
    }
}
