package br.com.alura.clientelo.store.interfaces.controller.order.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public class CreateOrderRequest {

    @NotNull
    private final Long customerId;
    private final List<OrderItemsInformation> information;

    public CreateOrderRequest(Long id, List<OrderItemsInformation> information) {
        this.customerId = id;
        this.information = information;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public List<OrderItemsInformation> getInformation() {
        return information;
    }
}
