package br.com.alura.clientelo.reportgenerator.report.impl;

import br.com.alura.clientelo.reportgenerator.report.Report;
import br.com.alura.clientelo.reportgenerator.report.ReportOrderDTO;
import br.com.alura.clientelo.reportgenerator.report.result.Result;
import br.com.alura.clientelo.reportgenerator.report.result.impl.SalesPerCategoryResult;
import br.com.alura.clientelo.store.infra.repository.category.vo.SalesPerCategoryVO;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Stream;

public class SalesPerCategory implements Report {

    public record Information(Integer numberOfOrders, BigDecimal totalAmount) { }

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

                List<ReportOrderDTO> ordersPerCategory = orders.stream()
                        .filter(order -> order.getCategory().equals(category))
                        .toList();

                Integer numberOfOrders = ordersPerCategory.stream()
                        .map(ReportOrderDTO::getQuantity)
                        .reduce(0, Integer::sum);

                BigDecimal totalAmount = ordersPerCategory.stream()
                        .map(ReportOrderDTO::getTotalAmount)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

                result.put(category, new Information(numberOfOrders, totalAmount));
            });

            return new SalesPerCategoryResult(result);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Error while processing report: %s", e));
        }
    }
    public Result processFromDatabase(List<SalesPerCategoryVO> information, Integer categoryLimit) {
        try {
            LinkedHashMap<String, Information> result = new LinkedHashMap<>();

            information.stream()
                    .limit(categoryLimit != null ? categoryLimit : information.size())
                    .forEach(info -> result.put(info.getName(), new Information(Math.toIntExact(info.getQuantity()), info.getTotalAmount())));

            return new SalesPerCategoryResult(result);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Error while processing report: %s", e));
        }
    }
}
