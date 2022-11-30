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
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class CreateOrderRequestConverter {

    public Order toOrder(List<OrderItemsInformation> information, OrderDiscountType discountType, Customer customer, List<Product> products) {
        Order order = new Order(
                LocalDate.now(),
                customer,
                discountType
        );

        List<OrderItem> items = new ArrayList<>();

        Set<Long> productIds = information.stream().map(OrderItemsInformation::getProductId).collect(Collectors.toSet());

        for (Long id : productIds) {
            Optional<Product> filteredProduct = products.stream()
                    .filter(product -> product.getId().equals(id))
                    .findAny();

            Integer quantity = information.stream()
                    .filter(info -> info.getProductId().equals(id))
                    .map(OrderItemsInformation::getQuantity)
                    .findFirst()
                    .orElseThrow();

            if (filteredProduct.isEmpty())
                throw new RuntimeException("Invalid product Id.");

            OrderItem orderItem = toOrderItem(quantity, order, filteredProduct.get());
            items.add(orderItem);
        }

        order.setItems(items);
        order.applyDiscount(discountType);

        return order;
    }

    private OrderItem toOrderItem(Integer quantity, Order order, Product product) {
        return new OrderItem(
                quantity,
                order,
                product,
                quantity > 10 ? OrderItemDiscountType.QUANTITY : OrderItemDiscountType.NONE
        );
    }
}
