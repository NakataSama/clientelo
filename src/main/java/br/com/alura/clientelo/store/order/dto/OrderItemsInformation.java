package br.com.alura.clientelo.store.order.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class OrderItemsInformation {

    @NotNull
    private final Long productId;
    @Min(1)
    private final int quantity;

    public OrderItemsInformation(Long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }
}
