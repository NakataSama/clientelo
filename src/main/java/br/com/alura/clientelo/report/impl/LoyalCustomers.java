package br.com.alura.clientelo.report.impl;

import br.com.alura.clientelo.order.Order;
import br.com.alura.clientelo.report.Report;
import br.com.alura.clientelo.report.result.impl.LoyalCustomersResult;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class LoyalCustomers implements Report {

    @Override
    public LoyalCustomersResult process(List<Order> orders) {

        LinkedHashMap<String, Integer> result;

        Stream<String> customers = orders.stream()
                .map(Order::getCustomer)
                .distinct()
                .sorted();

        result = customers.collect(toMap(customer -> customer, customer -> orders.stream()
                .filter(order -> order.getCustomer().equals(customer))
                .toList().size(), (k, v) -> k, LinkedHashMap::new));

        return new LoyalCustomersResult(result);
    }
}
