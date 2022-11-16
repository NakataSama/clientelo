package br.com.alura.clientelo.report.impl;

import br.com.alura.clientelo.report.ReportOrderDTO;
import br.com.alura.clientelo.report.Report;
import br.com.alura.clientelo.report.result.Result;
import br.com.alura.clientelo.report.result.impl.TopSellingProductsResult;

import java.util.Comparator;
import java.util.List;

public class TopSellingProducts implements Report {
    @Override
    public Result process(List<ReportOrderDTO> orders, Integer orderLimit) {
        try {
            if (orders == null || orders.isEmpty())
                throw new RuntimeException("No orders available");

            orders = orders.stream()
                    .sorted(Comparator.comparing(ReportOrderDTO::getQuantity).reversed())
                    .limit(orderLimit != null ? orderLimit : orders.size())
                    .toList();

            return new TopSellingProductsResult(orders);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Error while processing report: %s", e));
        }
    }
}
