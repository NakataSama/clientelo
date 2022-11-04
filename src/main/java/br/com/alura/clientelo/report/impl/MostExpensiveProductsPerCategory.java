package br.com.alura.clientelo.report.impl;

import br.com.alura.clientelo.dataprocessor.order.OrderDTO;
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
    public Result process(List<OrderDTO> orders, Integer categoryLimit) {
        try {
            if (orders == null || orders.isEmpty())
                throw new RuntimeException("No orders available");

            LinkedHashMap<String, Information> result = new LinkedHashMap<>();

            long categoryCount = orders.stream()
                    .map(OrderDTO::getCategory)
                    .distinct()
                    .count();

            Stream<String> categories = orders.stream()
                    .map(OrderDTO::getCategory)
                    .distinct()
                    .limit(categoryLimit != null ? categoryLimit : categoryCount)
                    .sorted();

            categories.forEach(category -> {

                List<OrderDTO> filteredOrders = orders.stream().filter(order -> order.getCategory().equals(category))
                        .sorted(Comparator.comparing(OrderDTO::getPrice).reversed()).toList();

                String product = filteredOrders.stream()
                        .map(OrderDTO::getProduct)
                        .findFirst().orElse("No product");

                BigDecimal price = filteredOrders.stream()
                        .map(OrderDTO::getPrice)
                        .findFirst().orElse(BigDecimal.ZERO);

                result.put(category, new Information(product, price));
            });

            return new MostExpensiveProductsPerCategoryResult(result);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Error while processing report: %s", e));
        }
    }
}
