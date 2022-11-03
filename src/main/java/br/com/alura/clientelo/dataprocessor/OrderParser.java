package br.com.alura.clientelo.dataprocessor;

import br.com.alura.clientelo.category.Category;
import br.com.alura.clientelo.customer.Address;
import br.com.alura.clientelo.customer.Customer;
import br.com.alura.clientelo.order.OrderItemDiscountType;
import br.com.alura.clientelo.order.DiscountType;
import br.com.alura.clientelo.order.Order;
import br.com.alura.clientelo.order.OrderItem;
import br.com.alura.clientelo.product.Product;
import com.fasterxml.jackson.databind.JsonNode;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OrderParser {

    public static List<OrderItem> parse(List<String[]> content) {
        return content.stream().skip(1)
                .map(order -> {
                    BigDecimal price = new BigDecimal(order[2]);
                    int quantity = Integer.parseInt(order[3]);
                    LocalDate date = LocalDate.parse(order[4], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    Customer customer = new Customer(order[5], "", "", new Address());
                    Category category = new Category(order[0], true);
                    Product product = new Product(
                            order[1],
                            price.divide(BigDecimal.valueOf(quantity), RoundingMode.FLOOR),
                            order[1],
                            0,
                            category);

                    Order newOrder = new Order(date, customer, BigDecimal.ZERO, DiscountType.NONE);
                    return new OrderItem(price, quantity, newOrder, product, BigDecimal.ZERO, OrderItemDiscountType.NONE);
                }).toList();
    }

    public static List<OrderItem> parse(JsonNode jsonNode) {
        List<OrderItem> response = new ArrayList<>();

        if (jsonNode.isArray()) {
            jsonNode.forEach(node -> {
                BigDecimal price = new BigDecimal(node.get("preco").asText());
                int quantity = node.get("quantidade").asInt();
                LocalDate date = LocalDate.parse(node.get("data").asText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                Customer customer = new Customer(node.get("cliente").asText(), "", "", new Address());
                Category category = new Category(node.get("categoria").asText(), true);
                Product product = new Product(
                        node.get("produto").asText(),
                        price.divide(BigDecimal.valueOf(quantity), RoundingMode.FLOOR),
                        node.get("produto").asText(),
                        0,
                        category);

                Order newOrder = new Order(date, customer, BigDecimal.ZERO, DiscountType.NONE);
                OrderItem orderItem =  new OrderItem(price, quantity, newOrder, product, BigDecimal.ZERO, OrderItemDiscountType.NONE);
                response.add(orderItem);
            });
        }
        return response;
    }
}
