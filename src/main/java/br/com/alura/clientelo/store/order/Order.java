package br.com.alura.clientelo.store.order;

import br.com.alura.clientelo.store.customer.Customer;
import br.com.alura.clientelo.store.orderitem.OrderItem;
import br.com.alura.clientelo.store.orderitem.OrderItemDiscountType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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

    public Order(LocalDate date, Customer customer) {
        this.date = date;
        this.customer = customer;
        this.orderDiscountType = OrderDiscountType.NONE;
        this.discount = orderDiscountType.getValue();
        this.totalAmount = BigDecimal.ZERO;
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

    public void addItems(List<OrderItem> itemsToAdd) {

        if(items == null)
            items = new ArrayList<>();

        boolean isOrderItemDiscountAvailable = itemsToAdd.stream()
                .anyMatch(item -> !item.getDiscountOrderItemType().equals(OrderItemDiscountType.NONE));

        if(isOrderItemDiscountAvailable) {

            Set<OrderItemDiscountType> orderItemDiscountTypes = itemsToAdd.stream()
                    .map(OrderItem::getDiscountOrderItemType)
                    .filter(orderItemDiscountType -> !orderItemDiscountType.equals(OrderItemDiscountType.NONE))
                    .collect(Collectors.toSet());

            orderItemDiscountTypes.forEach(orderItemDiscountType -> discount = discount.add(orderItemDiscountType.getValue()));

        }

        this.items.addAll(itemsToAdd);

        this.totalAmount = items.stream()
                .map(OrderItem::getTotalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void applyDiscount(OrderDiscountType discountToBeApplied) {

        if (discountToBeApplied.equals(OrderDiscountType.LOYALTY) && !items.isEmpty() && !orderDiscountType.equals(discountToBeApplied)) {

            this.discount = discount.add(discountToBeApplied.getValue());
            this.orderDiscountType = discountToBeApplied;
            BigDecimal discountedPrice = totalAmount.multiply(discount);
            this.totalAmount = totalAmount.subtract(discountedPrice);
        }
    }

    public void applyDiscountToItems(OrderItemDiscountType discountToBeApplied) {

        if ((discountToBeApplied.equals(OrderItemDiscountType.QUANTITY)
                || discountToBeApplied.equals(OrderItemDiscountType.SALE)) && (items != null && !items.isEmpty())) {

            BigDecimal originalDiscountValue = discount;
            this.discount = discount.add(discountToBeApplied.getValue());

            this.items.forEach(item -> item.applyDiscount(discountToBeApplied));

            this.totalAmount = items.stream()
                    .map(OrderItem::getTotalAmount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            BigDecimal discountedPrice = totalAmount.multiply(originalDiscountValue);
            this.totalAmount = totalAmount.subtract(discountedPrice);
        }
    }

}
