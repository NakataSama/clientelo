package br.com.alura.clientelo.reportgenerator.report.impl;

import br.com.alura.clientelo.reportgenerator.report.Report;
import br.com.alura.clientelo.reportgenerator.report.result.impl.TopSellingProductsResult;
import br.com.alura.clientelo.reportgenerator.report.ReportOrderDTO;
import br.com.alura.clientelo.reportgenerator.report.result.Result;
import br.com.alura.clientelo.store.infra.repository.product.vo.TopSellingProductsVO;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TopSellingProducts implements Report {

    public record Information(String category, String product, long quantity) { }
    @Override
    public Result process(List<ReportOrderDTO> orders, Integer orderLimit) {
        try {
            if (orders == null || orders.isEmpty())
                throw new RuntimeException("No orders available");

            orders = orders.stream()
                    .sorted(Comparator.comparing(ReportOrderDTO::getQuantity).reversed())
                    .limit(orderLimit != null ? orderLimit : orders.size())
                    .toList();

            List<Information> result = orders.stream()
                    .map(order -> new Information(order.getCategory(), order.getProduct(), order.getQuantity())).toList();

            return new TopSellingProductsResult(result);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Error while processing report: %s", e));
        }
    }

    public Result processFromDatabase(List<TopSellingProductsVO> information, Integer categoryLimit) {
        try {
            List<TopSellingProducts.Information> result = new ArrayList<>();

            information.stream()
                    .limit(categoryLimit != null ? categoryLimit : information.size())
                    .forEach(info -> {
                        result.add(new Information(info.getCategory(), info.getProduct(), info.getQuantity()));
                    });

            return new TopSellingProductsResult(result);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Error while processing report: %s", e));
        }
    }
}
