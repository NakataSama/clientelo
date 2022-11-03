package br.com.alura.clientelo.report.impl;

import br.com.alura.clientelo.order.OrderItem;
import br.com.alura.clientelo.report.Report;
import br.com.alura.clientelo.report.result.Result;
import br.com.alura.clientelo.report.result.impl.LoyalCustomersResult;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class LoyalCustomers implements Report {

    @Override
    public Result process(List<OrderItem> orders, Integer customerLimit) {
        try {
            if (orders == null || orders.isEmpty())
                throw new RuntimeException("No orders available");

            LinkedHashMap<String, Integer> result;

            long customerCount = orders.stream()
                    .map(order -> order.getOrder().getCustomer().getName())
                    .distinct()
                    .count();

            Stream<String> customers = orders.stream()
                    .map(order -> order.getOrder().getCustomer().getName())
                    .distinct()
                    .limit(customerLimit != null ? customerLimit : customerCount)
                    .sorted();

            result = customers.collect(toMap(customer -> customer, customer -> orders.stream()
                    .filter(order -> order.getOrder().getCustomer().getName().equals(customer))
                    .toList().size(), (k, v) -> k, LinkedHashMap::new));

            return new LoyalCustomersResult(result);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Error while processing report: %s", e));
        }
    }
}
