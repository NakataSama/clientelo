package br.com.alura.clientelo.store.adapter.controller.order.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class FindAllOrdersResponse {

    private LocalDate date;
    private BigDecimal value;
    private BigDecimal discount;
    private Integer productsQuantity;
    private OrderCustomer customer;

    public FindAllOrdersResponse(LocalDate date, BigDecimal value, BigDecimal discount, Integer productsQuantity, OrderCustomer customer) {
        this.date = date;
        this.value = value;
        this.discount = discount;
        this.productsQuantity = productsQuantity;
        this.customer = customer;
    }

    public LocalDate getDate() {
        return date;
    }

    public BigDecimal getValue() {
        return value;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public Integer getProductsQuantity() {
        return productsQuantity;
    }

    public OrderCustomer getCustomer() {
        return customer;
    }
}
