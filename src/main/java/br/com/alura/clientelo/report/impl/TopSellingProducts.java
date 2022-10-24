package br.com.alura.clientelo.report.impl;

import br.com.alura.clientelo.order.Order;
import br.com.alura.clientelo.report.Report;
import br.com.alura.clientelo.report.result.impl.TopSellingProductsResult;

import java.util.Comparator;
import java.util.List;

public class TopSellingProducts implements Report {
    @Override
    public TopSellingProductsResult process(List<Order> orders) {

        orders = orders.stream()
                .sorted(Comparator.comparing(Order::getQuantity).reversed())
                .limit(3)
                .toList();

        return new TopSellingProductsResult(orders);
    }
}
