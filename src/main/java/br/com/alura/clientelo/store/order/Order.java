package br.com.alura.clientelo.store.order;

import br.com.alura.clientelo.store.customer.Customer;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private BigDecimal discount;

    @Enumerated(EnumType.STRING)
    private DiscountType discountType;

    @Transient
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    private List<OrderItem> items;

    public Order() {}

    public Order(LocalDate date, Customer customer, BigDecimal discount, DiscountType discountType, List<OrderItem> items) {
        this.date = date;
        this.customer = customer;
        this.discount = discount;
        this.discountType = discountType;
        this.items = items;
    }

    public Order(LocalDate date, Customer customer, BigDecimal discount, DiscountType discountType) {
        this.date = date;
        this.customer = customer;
        this.discount = discount;
        this.discountType = discountType;
        this.items = items;
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

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(date, order.date) && Objects.equals(customer, order.customer) && Objects.equals(discount, order.discount) && discountType == order.discountType && Objects.equals(items, order.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, customer, discount, discountType, items);
    }
}
