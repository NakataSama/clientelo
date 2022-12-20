package br.com.alura.clientelo.store.order.dto;

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
        private Long id;
        private String name;
        private String category;
        private Integer quantity;
        private BigDecimal price;
        private BigDecimal total;
        private BigDecimal discount;

        public ProductResponse() {
        }

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

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        public BigDecimal getTotal() {
            return total;
        }

        public void setTotal(BigDecimal total) {
            this.total = total;
        }

        public BigDecimal getDiscount() {
            return discount;
        }

        public void setDiscount(BigDecimal discount) {
            this.discount = discount;
        }
    }
}
