package pl.pawkowal.medicalclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.pawkowal.medicalclinic.address.domain.Address;
import pl.pawkowal.medicalclinic.address.infrastructure.AddressRepository;
import pl.pawkowal.medicalclinic.doctor.domain.Doctor;
import pl.pawkowal.medicalclinic.doctor.infrastructure.DoctorRepository;
import pl.pawkowal.medicalclinic.medicalTreatment.domain.MedicalTreatment;
import pl.pawkowal.medicalclinic.medicalTreatment.domain.TreatmentType;
import pl.pawkowal.medicalclinic.medicalTreatment.infrastructure.MedicalTreatmentRepository;
import pl.pawkowal.medicalclinic.patient.domain.Patient;
import pl.pawkowal.medicalclinic.patient.infrastructure.PatientRepository;
import pl.pawkowal.medicalclinic.visit.domain.Visit;
import pl.pawkowal.medicalclinic.visit.domain.VisitStatus;
import pl.pawkowal.medicalclinic.visit.infrastructure.VisitRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@Profile("dev")
public class DevDataSeeder implements CommandLineRunner {

    private final PatientRepository patientRepository;

    private final AddressRepository addressRepository;

    private final DoctorRepository doctorRepository;

    private final VisitRepository visitRepository;

    private final MedicalTreatmentRepository medicalTreatmentRepository;


    public DevDataSeeder(PatientRepository patientRepository, AddressRepository addressRepository, DoctorRepository doctorRepository, VisitRepository visitRepository, MedicalTreatmentRepository medicalTreatmentRepository) {
        this.patientRepository = patientRepository;
        this.addressRepository = addressRepository;
        this.doctorRepository = doctorRepository;
        this.visitRepository = visitRepository;
        this.medicalTreatmentRepository = medicalTreatmentRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {

        Patient p1 = patientRepository.save(new Patient(
                "Adam",
                "Nowak",
                LocalDate.of(1985, 12, 22),
                "123123123",
                "adam.nowak@local.dev"
        ));

        Patient p2 = patientRepository.save(new Patient(
                "Marcin",
                "Rutkowski",
                LocalDate.of(1959, 7, 1),
                "00444444444",
                null
        ));

        Patient p3 = patientRepository.save(new Patient(
                "Wiktoria",
                "Kwiatkowska",
                LocalDate.of(1999, 3, 12),
                "+48 676767676",
                "wiktoria.kwiatkowska@local.dev"
        ));

        Patient p4 = patientRepository.save(new Patient(
                "Anna",
                "Lis",
                LocalDate.of(1987, 1, 31),
                "222333222",
                "anna.lis@local.dev"
        ));

        Patient p5 = patientRepository.save(new Patient(
                "Jakub",
                "Lis",
                LocalDate.of(2015, 10, 25),
                "111222333",
                null
        ));

        Address a1 = addressRepository.save(new Address(
                "Wrocław",
                "Legnicka",
                "50-222",
                "4/60"
        ));

        Address a2 = addressRepository.save(new Address(
                "Legnica",
                "Wrocławska",
                "59-220",
                "56/3"
        ));

        Address a3 = addressRepository.save(new Address(
                "Wrocław",
                "pl. Grunwaldzki",
                "50-788",
                "14/1"
        ));

        Doctor d1 = doctorRepository.save(new Doctor(
                a1,
                "Dariusz",
                "Kwaśniewski",
                "555444333",
                "dariusz.kwasniewski@local.dev"
        ));

        Doctor d2 = doctorRepository.save(new Doctor(
                a2,
                "Magdalena",
                "Zielińska",
                "555777888",
                "magdalena.zielinska@local.dev"
        ));

        Doctor d3 = doctorRepository.save(new Doctor(
                a3,
                "Tomasz",
                "Mazur",
                "555111222",
                "tomasz.mazur@local.dev"
        ));

        Visit v1 = visitRepository.save(new Visit(
                p1,
                d1,
                LocalDateTime.now().plusDays(2),
                new BigDecimal("250.00"),
                VisitStatus.SCHEDULED
        ));

        Visit v2 = visitRepository.save(new Visit(
                p2,
                d1,
                LocalDateTime.now().minusDays(5),
                new BigDecimal("300.00"),
                VisitStatus.COMPLETED
        ));

        Visit v3 = visitRepository.save(new Visit(
                p3,
                d2,
                LocalDateTime.of(2026, 3, 15, 14, 30),
                new BigDecimal("180.00"),
                VisitStatus.SCHEDULED
        ));

        Visit v4 = visitRepository.save(new Visit(
                p4,
                d3,
                LocalDateTime.of(2026, 3, 27, 13, 0),
                new BigDecimal("150.00"),
                VisitStatus.CANCELED
        ));

        medicalTreatmentRepository.save(new MedicalTreatment(
                v2,
                TreatmentType.EXAMINATION,
                "General medical check and blood pressure measurement"
        ));

        medicalTreatmentRepository.save(new MedicalTreatment(
                v2,
                TreatmentType.PRESCRIPTION,
                "Prescribed antibiotics for 7 days"
        ));

        medicalTreatmentRepository.save(new MedicalTreatment(
                v4,
                TreatmentType.CONSULTATION,
                "Initial consultation canceled due to patient absence"
        ));
    }
}
