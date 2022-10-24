package br.com.alura.clientelo.report.impl;

import br.com.alura.clientelo.order.Order;
import br.com.alura.clientelo.report.Report;
import br.com.alura.clientelo.report.result.impl.SalesPerCategoryResult;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Stream;

public class SalesPerCategory implements Report {

    public record Information(Integer numberOfOrders, BigDecimal totalAmount) { }

    @Override
    public SalesPerCategoryResult process(List<Order> orders) {

        LinkedHashMap<String, Information> result = new LinkedHashMap<>();

        Stream<String> categories = orders.stream()
                .map(Order::getCategory)
                .distinct()
                .sorted();

        categories.forEach(category -> {

            List<Order> ordersPerCategory = orders.stream()
                    .filter(order -> order.getCategory().equals(category))
                    .toList();

            Integer numberOfOrders = ordersPerCategory.size();

            BigDecimal totalAmount = ordersPerCategory.stream()
                    .map(Order::getTotalAmount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            result.put(category, new Information(numberOfOrders, totalAmount));
        });

        return new SalesPerCategoryResult(result);
    }
}
