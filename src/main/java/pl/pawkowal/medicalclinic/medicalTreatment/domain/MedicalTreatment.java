package pl.pawkowal.medicalclinic.medicalTreatment.domain;

import jakarta.persistence.*;
import pl.pawkowal.medicalclinic.visit.domain.Visit;

@Entity
@Table(name = "medical_treatments")
public class MedicalTreatment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "visit_id", nullable = false)
    private Visit visit;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TreatmentType treatmentType;

    @Column(nullable = false, length = 500)
    private String description;

    protected MedicalTreatment() {}

    public MedicalTreatment(Visit visit, TreatmentType treatmentType, String description) {
        this.visit = visit;
        this.treatmentType = treatmentType;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public Visit getVisit() {
        return visit;
    }

    public TreatmentType getTreatmentType() {
        return treatmentType;
    }

    public String getDescription() { return description; }

    public void update(TreatmentType treatmentType, String description) {
        this.treatmentType = treatmentType;
        this.description = description;
    }
}
