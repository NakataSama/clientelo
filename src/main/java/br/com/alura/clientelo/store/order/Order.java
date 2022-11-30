package br.com.alura.clientelo.store.order;

import br.com.alura.clientelo.store.customer.Customer;
import br.com.alura.clientelo.store.orderitem.OrderItem;
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

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private BigDecimal discount;

    @Enumerated(EnumType.STRING)
    private OrderDiscountType orderDiscountType;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderItem> items;

    @Transient
    private BigDecimal totalAmount;

    public Order() {}

    public Order(LocalDate date, Customer customer, OrderDiscountType orderDiscountType) {
        this.date = date;
        this.customer = customer;
        applyDiscount(orderDiscountType);
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

    public OrderDiscountType getDiscountType() {
        return orderDiscountType;
    }

    public void setDiscountType(OrderDiscountType orderDiscountType) {
        this.orderDiscountType = orderDiscountType;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
        this.totalAmount = items.stream().map(OrderItem::getTotalAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(date, order.date) && Objects.equals(customer, order.customer) && Objects.equals(discount, order.discount) && orderDiscountType == order.orderDiscountType && Objects.equals(items, order.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, customer, discount, orderDiscountType, items);
    }

    public void applyDiscount(OrderDiscountType discountType) {
        this.discount = discountType.getValue();
        this.orderDiscountType = discountType;

        if (discountType.equals(OrderDiscountType.LOYALTY) && !items.isEmpty()) {
            BigDecimal discountedPrice = totalAmount.multiply(discount);
            this.totalAmount = totalAmount.subtract(discountedPrice);
        }
    }
}
