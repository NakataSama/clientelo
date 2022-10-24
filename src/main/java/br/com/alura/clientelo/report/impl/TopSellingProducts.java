package br.com.alura.clientelo.report.impl;

import br.com.alura.clientelo.order.Order;
import br.com.alura.clientelo.report.Report;
import br.com.alura.clientelo.report.result.Result;
import br.com.alura.clientelo.report.result.impl.TopSellingProductsResult;

import java.util.Comparator;
import java.util.List;

public class TopSellingProducts implements Report {
    @Override
    public Result process(List<Order> orders, Integer orderLimit) {

        orders = orders.stream()
                .sorted(Comparator.comparing(Order::getQuantity).reversed())
                .limit(orderLimit != null ? orderLimit : orders.size())
                .toList();

        return new TopSellingProductsResult(orders);
    }
}
