package br.com.alura.clientelo.store.order.dto;

import br.com.alura.clientelo.store.customer.Customer;
import br.com.alura.clientelo.store.order.Order;
import br.com.alura.clientelo.store.order.OrderDiscountType;
import br.com.alura.clientelo.store.orderitem.OrderItem;
import br.com.alura.clientelo.store.orderitem.OrderItemDiscountType;
import br.com.alura.clientelo.store.product.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CreateOrderRequestConverter {

    public Order toOrder(List<OrderItemsInformation> information, OrderDiscountType discountType, Customer customer, List<Product> products) {
        Order order = new Order(
                LocalDate.now(),
                customer
        );

        List<OrderItem> items = new ArrayList<>();

        List<Long> productIds = information.stream().map(OrderItemsInformation::getProductId).toList();

        for (Long id : productIds) {

            Product filteredProduct = products.stream()
                    .filter(product -> product.getId().equals(id))
                    .findAny()
                    .orElseThrow();

            Integer quantity = information.stream()
                    .filter(info -> info.getProductId().equals(id))
                    .map(OrderItemsInformation::getQuantity)
                    .findFirst()
                    .orElseThrow();

            OrderItemDiscountType orderItemDiscountType = quantity > 10 ? OrderItemDiscountType.QUANTITY : OrderItemDiscountType.NONE;
            OrderItem orderItem = toOrderItem(quantity, order, filteredProduct);
            orderItem.applyDiscount(orderItemDiscountType);
            items.add(orderItem);
        }

        order.addItems(items);
        order.applyDiscount(discountType);
        order.getItems().forEach(orderItem -> orderItem.setOrder(order));

        return order;
    }

    private OrderItem toOrderItem(Integer quantity, Order order, Product product) {
        return new OrderItem(
                quantity,
                order,
                product
        );
    }
}
