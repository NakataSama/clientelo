package br.com.alura.clientelo.report.impl;

import br.com.alura.clientelo.order.Order;
import br.com.alura.clientelo.report.Report;
import br.com.alura.clientelo.report.result.impl.GeneralReportResult;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

public class GeneralReport implements Report {

    public record Information(Integer numberOfOrders,
                              Integer productsSold,
                              Integer categories,
                              BigDecimal totalAmount,
                              Map<String, BigDecimal> leastProfitableOrder,
                              Map<String, BigDecimal> mostProfitableOrder) { }

    @Override
    public GeneralReportResult process(List<Order> orders) {

        Integer numberOfOrders = orders.size();

        Integer productsSold = orders.stream()
                .map(Order::getQuantity)
                .reduce(0, Integer::sum);

        Integer categories = Math.toIntExact(orders.stream()
                .map(Order::getCategory)
                .distinct()
                .count());

        BigDecimal totalAmount = orders.stream()
                .map(Order::getTotalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Map<String, BigDecimal> leastProfitableOrder = orders.stream()
                .min(comparing(Order::getTotalAmount))
                .map(order -> Map.of(order.getProduct(), order.getTotalAmount()))
                .orElse(Map.of("No order", BigDecimal.ZERO));

        Map<String, BigDecimal> mostProfitableOrder = orders.stream()
                .max(comparing(Order::getTotalAmount))
                .map(order -> Map.of(order.getProduct(), order.getTotalAmount()))
                .orElse(Map.of("No order", BigDecimal.ZERO));

        return new GeneralReportResult(new Information(numberOfOrders, productsSold, categories, totalAmount, leastProfitableOrder, mostProfitableOrder));
    }
}
