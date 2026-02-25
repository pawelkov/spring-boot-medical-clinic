package pl.pawkowal.medicalclinic.address.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String city;

    @Column(nullable = false, length = 100)
    private String street;

    @Column(nullable = false, length = 100)
    private String postalCode;

    @Column(nullable = false, length = 100)
    private String addressLine;

    protected Address() {}

    public Address(String city, String street, String postalCode, String addressLine) {
        this.city = city;
        this.street = street;
        this.postalCode = postalCode;
        this.addressLine = addressLine;
    }

    public Long getId() {
        return id;
    }

    public String getCity() { return city; }

    public String getStreet() {
        return street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void update(String city, String street, String postalCode, String addressLine) {
        this.city = city;
        this.street = street;
        this.postalCode = postalCode;
        this.addressLine = addressLine;
    }
}
