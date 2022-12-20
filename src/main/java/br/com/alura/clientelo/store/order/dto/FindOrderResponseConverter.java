package br.com.alura.clientelo.store.order.dto;

import br.com.alura.clientelo.store.core.entity.order.Order;
import br.com.alura.clientelo.store.core.entity.orderitem.OrderItem;

import java.util.List;

public class FindOrderResponseConverter {


    public FindOrderResponse from(Order order) {
        return new FindOrderResponse(
                order.getDate(),
                order.getTotalAmount(),
                order.getDiscount(),
                createProductReponseList(order.getItems()),
                new OrderCustomer(order.getCustomer().getId(), order.getCustomer().getName()));
    }

    private List<FindOrderResponse.ProductResponse> createProductReponseList(List<OrderItem> products) {
        return products.stream()
                .map(orderItem -> {
                    return new FindOrderResponse.ProductResponse(
                            orderItem.getId(),
                            orderItem.getProduct().getName(),
                            orderItem.getProduct().getCategory().getName(),
                            orderItem.getQuantity(),
                            orderItem.getPrice(),
                            orderItem.getTotalAmount(),
                            orderItem.getDiscount()
                    );
                }).toList();
    }
}
