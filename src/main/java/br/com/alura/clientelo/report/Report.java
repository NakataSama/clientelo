package br.com.alura.clientelo.report;

import br.com.alura.clientelo.store.order.OrderItem;
import br.com.alura.clientelo.report.result.Result;

import java.util.List;

public interface Report {
    Result process(List<OrderItem> orders, Integer limiter) throws Exception;
}
