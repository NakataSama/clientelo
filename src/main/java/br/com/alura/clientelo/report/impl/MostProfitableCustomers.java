package br.com.alura.clientelo.report.impl;

import br.com.alura.clientelo.order.Order;
import br.com.alura.clientelo.report.Report;
import br.com.alura.clientelo.report.result.Result;
import br.com.alura.clientelo.report.result.impl.MostProfitableCustomersResult;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;

public class MostProfitableCustomers implements Report {

    public record Information(Integer numberOfOrders, BigDecimal totalAmount) { }

    @Override
    public Result process(List<Order> orders, Integer customerLimit) {
        try {
            if (orders == null || orders.isEmpty())
                throw new RuntimeException("No orders available");

            LinkedHashMap<String, Information> result = new LinkedHashMap<>();

            long customerCount = orders.stream()
                    .map(Order::getCustomer)
                    .distinct()
                    .count();

            Stream<String> customers = orders.stream()
                    .sorted(comparing(Order::getTotalAmount).reversed())
                    .map(Order::getCustomer)
                    .distinct()
                    .limit(customerLimit != null ? customerLimit : customerCount);

            customers.forEach(customer -> {

                List<Order> ordersByCustomer = orders.stream()
                        .filter(order -> order.getCustomer().equals(customer))
                        .toList();

                Integer numberOfOrders = ordersByCustomer.size();

                BigDecimal totalAmount = ordersByCustomer.stream()
                        .map(Order::getTotalAmount)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

                result.put(customer, new Information(numberOfOrders, totalAmount));
            });

            return new MostProfitableCustomersResult(result);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Error while processing report: %s", e));
        }
    }
}
