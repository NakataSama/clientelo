package br.com.alura.clientelo.report.impl;

import br.com.alura.clientelo.order.Order;
import br.com.alura.clientelo.report.Report;
import br.com.alura.clientelo.report.result.Result;
import br.com.alura.clientelo.report.result.impl.MostExpensiveProductsPerCategoryResult;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Stream;

public class MostExpensiveProductsPerCategory implements Report {

    public record Information(String product, BigDecimal price) { }

    @Override
    public Result process(List<Order> orders, Integer categoryLimit) {

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

            List<Order> filteredOrders = orders.stream().filter(order -> order.getCategory().equals(category))
                    .sorted(Comparator.comparing(Order::getPrice).reversed()).toList();

            String product = filteredOrders.stream()
                    .map(Order::getProduct)
                    .findFirst().orElse("No product");

            BigDecimal price = filteredOrders.stream()
                    .map(Order::getPrice)
                    .findFirst().orElse(BigDecimal.ZERO);

            result.put(category, new Information(product, price));
        });

        return new MostExpensiveProductsPerCategoryResult(result);
    }
}