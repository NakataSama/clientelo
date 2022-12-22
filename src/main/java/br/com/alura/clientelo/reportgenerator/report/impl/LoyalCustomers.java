package br.com.alura.clientelo.reportgenerator.report.impl;

import br.com.alura.clientelo.reportgenerator.report.Report;
import br.com.alura.clientelo.reportgenerator.report.ReportOrderDTO;
import br.com.alura.clientelo.reportgenerator.report.result.Result;
import br.com.alura.clientelo.reportgenerator.report.result.impl.LoyalCustomersResult;
import br.com.alura.clientelo.store.infra.repository.customer.vo.LoyalCustomersVO;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class LoyalCustomers implements Report {

    @Override
    public Result process(List<ReportOrderDTO> orders, Integer customerLimit) {
        try {
            if (orders == null || orders.isEmpty())
                throw new RuntimeException("No orders available");

            LinkedHashMap<String, Integer> result;

            long customerCount = orders.stream()
                    .map(ReportOrderDTO::getCustomer)
                    .distinct()
                    .count();

            Stream<String> customers = orders.stream()
                    .map(ReportOrderDTO::getCustomer)
                    .distinct()
                    .limit(customerLimit != null ? customerLimit : customerCount)
                    .sorted();

            result = customers.collect(toMap(customer -> customer, customer -> orders.stream()
                    .filter(order -> order.getCustomer().equals(customer))
                    .toList().size(), (k, v) -> k, LinkedHashMap::new));

            return new LoyalCustomersResult(result);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Error while processing report: %s", e));
        }
    }

    public Result processFromDatabase(List<LoyalCustomersVO> information, Integer customerLimit) {
        try {
            LinkedHashMap<String, Integer> result = new LinkedHashMap<>();

            information.stream()
                    .limit(customerLimit != null ? customerLimit : information.size())
                    .forEach(info -> result.put(info.getName(), Math.toIntExact(info.getNumberOfOrders())));

            return new LoyalCustomersResult(result);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Error while processing report: %s", e));
        }
    }
}