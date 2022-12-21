package br.com.alura.clientelo.store.adapter.controller.order.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class FindOrderResponse {
    
    private LocalDate date;
    private BigDecimal value;
    private BigDecimal discount;
    private List<ProductResponse> products;
    private OrderCustomer customer;

    public FindOrderResponse() {
    }

    public FindOrderResponse(LocalDate date, BigDecimal value, BigDecimal discount, List<ProductResponse> products, OrderCustomer customer) {
        this.date = date;
        this.value = value;
        this.discount = discount;
        this.products = products;
        this.customer = customer;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public List<ProductResponse> getProducts() {
        return products;
    }

    public void setProducts(List<ProductResponse> products) {
        this.products = products;
    }

    public OrderCustomer getCustomer() {
        return customer;
    }

    public void setCustomer(OrderCustomer customer) {
        this.customer = customer;
    }

    public static class ProductResponse {
        private final Long id;
        private final String name;
        private final String category;
        private final Integer quantity;
        private final BigDecimal price;
        private BigDecimal total;
        private BigDecimal discount;

        public ProductResponse(Long id, String name, String category, Integer quantity, BigDecimal price, BigDecimal total, BigDecimal discount) {
            this.id = id;
            this.name = name;
            this.category = category;
            this.quantity = quantity;
            this.price = price;
            this.total = total;
            this.discount = discount;
        }

        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getCategory() {
            return category;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public BigDecimal getTotal() {
            return total;
        }

        public BigDecimal getDiscount() {
            return discount;
        }
    }
}
