package br.com.alura.clientelo.report;

import br.com.alura.clientelo.order.Order;
import br.com.alura.clientelo.report.result.Result;

import java.util.List;

public interface Report {
    Result process(List<Order> orders, Integer limiter);
}
