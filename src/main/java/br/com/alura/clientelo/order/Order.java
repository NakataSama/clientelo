package br.com.alura.clientelo.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Order {
    @JsonProperty("categoria")
    private String category;
    @JsonProperty("produto")
    private String product;
    @JsonProperty("cliente")
    private String customer;
    @JsonProperty("preco")
    private BigDecimal price;
    @JsonProperty("quantidade")
    private int quantity;
    @JsonProperty("data")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;

    public Order() {}

    public Order(String category, String product, String customer, BigDecimal price, int quantity, LocalDate date) {
        this.category = category;
        this.product = product;
        this.customer = customer;
        this.price = price;
        this.quantity = quantity;
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public String getProduct() {
        return product;
    }

    public String getCustomer() {
        return customer;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public LocalDate getDate() {
        return date;
    }

    public BigDecimal getTotalAmount() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }

    @Override
    public String toString() {
        return "Order{" +
                "category='" + category + '\'' +
                ", product='" + product + '\'' +
                ", customer='" + customer + '\'' +
                ", price=" + "R$ "+ price +
                ", quantity=" + quantity +
                ", date=" + date +
                '}';
    }

}
