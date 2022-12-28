package br.com.alura.clientelo.store.domain.orderitem;

import br.com.alura.clientelo.store.domain.order.Order;
import br.com.alura.clientelo.store.domain.product.Product;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private BigDecimal price;

    private Integer quantity;
    @ManyToOne(targetEntity = Order.class)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private BigDecimal discount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderItemDiscountType orderItemDiscountType;

    public OrderItem() {}

    public OrderItem(Integer quantity, Order order, Product product) {
        this.quantity = quantity;
        this.order = order;
        this.product = product;
        this.price = product.getPrice().multiply(BigDecimal.valueOf(quantity));
        this.orderItemDiscountType = OrderItemDiscountType.NONE;
        this.discount = orderItemDiscountType.getValue();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public OrderItemDiscountType getDiscountOrderItemType() {
        return orderItemDiscountType;
    }

    public void setDiscountOrderItemType(OrderItemDiscountType orderItemDiscountType) {
        this.orderItemDiscountType = orderItemDiscountType;
    }

    public BigDecimal getTotalAmount() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(id, orderItem.id) && Objects.equals(price, orderItem.price) && Objects.equals(quantity, orderItem.quantity) && Objects.equals(order, orderItem.order) && Objects.equals(product, orderItem.product) && Objects.equals(discount, orderItem.discount) && orderItemDiscountType == orderItem.orderItemDiscountType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, quantity, order, product, discount, orderItemDiscountType);
    }

    public void applyDiscount(OrderItemDiscountType discountToBeApplied) {

        if ((discountToBeApplied.equals(OrderItemDiscountType.QUANTITY)
                || discountToBeApplied.equals(OrderItemDiscountType.SALE)) && !orderItemDiscountType.equals(discountToBeApplied)) {

            this.orderItemDiscountType = discountToBeApplied;
            this.discount = discount.add(discountToBeApplied.getValue());
            BigDecimal discountedPrice = price.multiply(discount);
            this.price = price.subtract(discountedPrice);
        }
    }
}