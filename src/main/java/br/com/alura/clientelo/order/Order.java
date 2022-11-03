package br.com.alura.clientelo.order;

import br.com.alura.clientelo.customer.Customer;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private BigDecimal discount;

    @Enumerated(EnumType.STRING)
    private DiscountType discountType;

    public Order() {}

    public Order(LocalDate date, Customer customer, BigDecimal discount, DiscountType discountType) {
        this.date = date;
        this.customer = customer;
        this.discount = discount;
        this.discountType = discountType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public void setDiscountType(DiscountType discountType) {
        this.discountType = discountType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(date, order.date) && Objects.equals(customer, order.customer) && Objects.equals(discount, order.discount) && discountType == order.discountType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, customer, discount, discountType);
    }
}
