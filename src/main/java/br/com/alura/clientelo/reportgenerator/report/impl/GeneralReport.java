package br.com.alura.clientelo.reportgenerator.report.impl;

import br.com.alura.clientelo.reportgenerator.report.Report;
import br.com.alura.clientelo.reportgenerator.report.ReportOrderDTO;
import br.com.alura.clientelo.reportgenerator.report.result.impl.GeneralReportResult;
import br.com.alura.clientelo.reportgenerator.report.result.Result;

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
    public Result process(List<ReportOrderDTO> orders, Integer orderLimit) {
        try {
            if (orders == null || orders.isEmpty())
                throw new RuntimeException("No orders available");

            if (orderLimit != null) orders = orders.stream().limit(orderLimit).toList();

            Integer numberOfOrders = orders.size();

            Integer productsSold = orders.stream()
                    .map(ReportOrderDTO::getQuantity)
                    .reduce(0, Integer::sum);

            Integer categories = Math.toIntExact(orders.stream()
                    .map(ReportOrderDTO::getCategory)
                    .distinct()
                    .count());

            BigDecimal totalAmount = orders.stream()
                    .map(ReportOrderDTO::getTotalAmount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            Map<String, BigDecimal> leastProfitableOrder = orders.stream()
                    .min(comparing(ReportOrderDTO::getTotalAmount))
                    .map(order -> Map.of(order.getProduct(), order.getTotalAmount()))
                    .orElse(Map.of("No order", BigDecimal.ZERO));

            Map<String, BigDecimal> mostProfitableOrder = orders.stream()
                    .max(comparing(ReportOrderDTO::getTotalAmount))
                    .map(order -> Map.of(order.getProduct(), order.getTotalAmount()))
                    .orElse(Map.of("No order", BigDecimal.ZERO));

            return new GeneralReportResult(new Information(numberOfOrders, productsSold, categories, totalAmount, leastProfitableOrder, mostProfitableOrder));
        } catch (Exception e) {
            throw new RuntimeException(String.format("Error while processing report: %s", e));
        }
    }
}
