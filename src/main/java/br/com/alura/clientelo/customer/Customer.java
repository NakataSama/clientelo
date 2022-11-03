package br.com.alura.clientelo.customer;


import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private String documentNumber;
    private String phone;
    @Embedded
    private Address address;

    public Customer() {}

    public Customer(String name, String documentNumber, String phone, Address address) {
        this.name = name;
        this.documentNumber = documentNumber;
        this.phone = phone;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) && Objects.equals(name, customer.name) && Objects.equals(documentNumber, customer.documentNumber) && Objects.equals(phone, customer.phone) && Objects.equals(address, customer.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, documentNumber, phone, address);
    }
}
