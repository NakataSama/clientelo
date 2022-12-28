package br.com.alura.clientelo.reportgenerator.domain.report.impl;

import br.com.alura.clientelo.reportgenerator.domain.report.Report;
import br.com.alura.clientelo.reportgenerator.domain.report.dto.ReportOrderDTO;
import br.com.alura.clientelo.reportgenerator.domain.result.Result;
import br.com.alura.clientelo.reportgenerator.domain.result.impl.MostProfitableCustomersResult;
import br.com.alura.clientelo.store.infra.repository.customer.vo.MostProfitableCustomersVO;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;

public class MostProfitableCustomers implements Report {

    public record Information(Integer numberOfOrders, BigDecimal totalAmount) { }

    @Override
    public Result process(List<ReportOrderDTO> orders, Integer customerLimit) {
        try {
            if (orders == null || orders.isEmpty())
                throw new RuntimeException("No orders available");

            LinkedHashMap<String, Information> result = new LinkedHashMap<>();

            long customerCount = orders.stream()
                    .map(ReportOrderDTO::getCustomer)
                    .distinct()
                    .count();

            Stream<String> customers = orders.stream()
                    .sorted(comparing(ReportOrderDTO::getTotalAmount).reversed())
                    .map(ReportOrderDTO::getCustomer)
                    .distinct()
                    .limit(customerLimit != null ? customerLimit : customerCount);

            customers.forEach(customer -> {

                List<ReportOrderDTO> ordersByCustomer = orders.stream()
                        .filter(order -> order.getCustomer().equals(customer))
                        .toList();

                Integer numberOfOrders = ordersByCustomer.size();

                BigDecimal totalAmount = ordersByCustomer.stream()
                        .map(ReportOrderDTO::getTotalAmount)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

                result.put(customer, new Information(numberOfOrders, totalAmount));
            });

            return new MostProfitableCustomersResult(result);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Error while processing report: %s", e));
        }
    }

    public Result processFromDatabase(List<MostProfitableCustomersVO> information, Integer customerLimit) {
        try {
            LinkedHashMap<String, Information> result = new LinkedHashMap<>();

            information.stream()
                    .limit(customerLimit != null ? customerLimit : information.size())
                    .forEach(info -> result.put(info.getName(), new Information(Math.toIntExact(info.getNumberOfOrders()), info.getTotalAmount())));

            return new MostProfitableCustomersResult(result);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Error while processing report: %s", e));
        }
    }
}
