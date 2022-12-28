package br.com.alura.clientelo.store.domain.customer;


import br.com.alura.clientelo.store.domain.order.Order;
import br.com.alura.clientelo.store.domain.user.User;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;
    private String documentNumber;
    private String phone;
    @Embedded
    private Address address;

    @Transient
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List<Order> orders;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Deprecated
    public Customer() {}

    public Customer(String name, String documentNumber, String phone, Address address, String email, String password) {
        this.name = name;
        this.documentNumber = documentNumber;
        this.phone = phone;
        this.address = address;
        this.user = new User(email, password);
    }

    public Customer(Long id, String name, String documentNumber, String phone, Address address, List<Order> orders, User user) {
        this.id = id;
        this.name = name;
        this.documentNumber = documentNumber;
        this.phone = phone;
        this.address = address;
        this.orders = orders;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) && Objects.equals(name, customer.name) && Objects.equals(documentNumber, customer.documentNumber) && Objects.equals(phone, customer.phone) && Objects.equals(address, customer.address) && Objects.equals(orders, customer.orders) && Objects.equals(user, customer.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, documentNumber, phone, address, orders, user);
    }
}
