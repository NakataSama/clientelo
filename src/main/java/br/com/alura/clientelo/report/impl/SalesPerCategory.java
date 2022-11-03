package br.com.alura.clientelo.report.impl;

import br.com.alura.clientelo.order.OrderItem;
import br.com.alura.clientelo.report.Report;
import br.com.alura.clientelo.report.result.Result;
import br.com.alura.clientelo.report.result.impl.SalesPerCategoryResult;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Stream;

public class SalesPerCategory implements Report {

    public record Information(Integer numberOfOrders, BigDecimal totalAmount) { }

    @Override
    public Result process(List<OrderItem> orders, Integer categoryLimit) {
        try {
            if (orders == null || orders.isEmpty())
                throw new RuntimeException("No orders available");

            LinkedHashMap<String, Information> result = new LinkedHashMap<>();

            long categoryCount = orders.stream()
                    .map(order -> order.getProduct().getCategory().getName())
                    .distinct()
                    .count();

            Stream<String> categories = orders.stream()
                    .map(order -> order.getProduct().getCategory().getName())
                    .distinct()
                    .limit(categoryLimit != null ? categoryLimit : categoryCount)
                    .sorted();

            categories.forEach(category -> {

                List<OrderItem> ordersPerCategory = orders.stream()
                        .filter(order -> order.getProduct().getCategory().getName().equals(category))
                        .toList();

                Integer numberOfOrders = ordersPerCategory.stream()
                        .map(OrderItem::getQuantity)
                        .reduce(0, Integer::sum);

                BigDecimal totalAmount = ordersPerCategory.stream()
                        .map(OrderItem::getTotalAmount)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

                result.put(category, new Information(numberOfOrders, totalAmount));
            });

            return new SalesPerCategoryResult(result);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Error while processing report: %s", e));
        }
    }
}
