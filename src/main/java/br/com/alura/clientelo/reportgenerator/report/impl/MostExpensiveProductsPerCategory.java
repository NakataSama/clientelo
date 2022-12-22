package br.com.alura.clientelo.reportgenerator.report.impl;

import br.com.alura.clientelo.reportgenerator.report.Report;
import br.com.alura.clientelo.reportgenerator.report.ReportOrderDTO;
import br.com.alura.clientelo.reportgenerator.report.result.Result;
import br.com.alura.clientelo.reportgenerator.report.result.impl.MostExpensiveProductsPerCategoryResult;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Stream;

public class MostExpensiveProductsPerCategory implements Report {

    public record Information(String product, BigDecimal price) { }

    @Override
    public Result process(List<ReportOrderDTO> orders, Integer categoryLimit) {
        try {
            if (orders == null || orders.isEmpty())
                throw new RuntimeException("No orders available");

            LinkedHashMap<String, Information> result = new LinkedHashMap<>();

            long categoryCount = orders.stream()
                    .map(ReportOrderDTO::getCategory)
                    .distinct()
                    .count();

            Stream<String> categories = orders.stream()
                    .map(ReportOrderDTO::getCategory)
                    .distinct()
                    .limit(categoryLimit != null ? categoryLimit : categoryCount)
                    .sorted();

            categories.forEach(category -> {

                List<ReportOrderDTO> filteredOrders = orders.stream().filter(order -> order.getCategory().equals(category))
                        .sorted(Comparator.comparing(ReportOrderDTO::getPrice).reversed()).toList();

                String product = filteredOrders.stream()
                        .map(ReportOrderDTO::getProduct)
                        .findFirst().orElse("No product");

                BigDecimal price = filteredOrders.stream()
                        .map(ReportOrderDTO::getPrice)
                        .findFirst().orElse(BigDecimal.ZERO);

                result.put(category, new Information(product, price));
            });

            return new MostExpensiveProductsPerCategoryResult(result);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Error while processing report: %s", e));
        }
    }
}
