package br.com.alura.clientelo.report.impl;

import br.com.alura.clientelo.order.OrderItem;
import br.com.alura.clientelo.report.Report;
import br.com.alura.clientelo.report.result.Result;
import br.com.alura.clientelo.report.result.impl.GeneralReportResult;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static java.util.Comparator.comparing;

public class GeneralReport implements Report {

    public record Information(Integer numberOfOrders,
                              Integer productsSold,
                              Integer categories,
                              BigDecimal totalAmount,
                              Map<String, BigDecimal> leastProfitableOrder,
                              Map<String, BigDecimal> mostProfitableOrder) { }

    @Override
    public Result process(List<OrderItem> orders, Integer orderLimit) {
        try {
            if (orders == null || orders.isEmpty())
                throw new RuntimeException("No orders available");

            if (orderLimit != null) orders = orders.stream().limit(orderLimit).toList();

            Integer numberOfOrders = orders.size();

            Integer productsSold = orders.stream()
                    .map(OrderItem::getQuantity)
                    .reduce(0, Integer::sum);

            Integer categories = Math.toIntExact(orders.stream()
                    .map(order -> order.getProduct().getCategory().getName())
                    .distinct()
                    .count());

            BigDecimal totalAmount = orders.stream()
                    .map(OrderItem::getTotalAmount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            Map<String, BigDecimal> leastProfitableOrder = orders.stream()
                    .min(comparing(OrderItem::getTotalAmount))
                    .map(order -> Map.of(order.getProduct().getName(), order.getTotalAmount()))
                    .orElse(Map.of("No order", BigDecimal.ZERO));

            Map<String, BigDecimal> mostProfitableOrder = orders.stream()
                    .max(comparing(OrderItem::getTotalAmount))
                    .map(order -> Map.of(order.getProduct().getName(), order.getTotalAmount()))
                    .orElse(Map.of("No order", BigDecimal.ZERO));

            return new GeneralReportResult(new Information(numberOfOrders, productsSold, categories, totalAmount, leastProfitableOrder, mostProfitableOrder));
        } catch (Exception e) {
            throw new RuntimeException(String.format("Error while processing report: %s", e));
        }
    }
}
