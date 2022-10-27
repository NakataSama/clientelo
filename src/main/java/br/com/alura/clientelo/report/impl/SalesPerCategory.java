package br.com.alura.clientelo.report.impl;

import br.com.alura.clientelo.order.Order;
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
    public Result process(List<Order> orders, Integer categoryLimit) {
        try {
            if (orders == null || orders.isEmpty())
                throw new RuntimeException("No orders available");

            LinkedHashMap<String, Information> result = new LinkedHashMap<>();

            long categoryCount = orders.stream()
                    .map(Order::getCategory)
                    .distinct()
                    .count();

            Stream<String> categories = orders.stream()
                    .map(Order::getCategory)
                    .distinct()
                    .limit(categoryLimit != null ? categoryLimit : categoryCount)
                    .sorted();

            categories.forEach(category -> {

                List<Order> ordersPerCategory = orders.stream()
                        .filter(order -> order.getCategory().equals(category))
                        .toList();

                Integer numberOfOrders = ordersPerCategory.stream()
                        .map(Order::getQuantity)
                        .reduce(0, Integer::sum);

                BigDecimal totalAmount = ordersPerCategory.stream()
                        .map(Order::getTotalAmount)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

                result.put(category, new Information(numberOfOrders, totalAmount));
            });

            return new SalesPerCategoryResult(result);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Error while processing report: %s", e));
        }
    }
}
