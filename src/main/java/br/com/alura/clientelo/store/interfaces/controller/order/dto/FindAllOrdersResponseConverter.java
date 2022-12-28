package br.com.alura.clientelo.store.interfaces.controller.order.dto;

import br.com.alura.clientelo.store.domain.order.Order;
import br.com.alura.clientelo.store.domain.orderitem.OrderItem;

public class FindAllOrdersResponseConverter {

    public FindAllOrdersResponse from(Order order) {

        return new FindAllOrdersResponse(
                order.getDate(),
                order.getTotalAmount(),
                order.getDiscount(),
                order.getItems().stream().map(OrderItem::getQuantity).reduce(0, Integer::sum),
                new OrderCustomer(
                        order.getCustomer().getId(),
                        order.getCustomer().getName()
                )
        );
    }
}