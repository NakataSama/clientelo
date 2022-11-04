package br.com.alura.clientelo.report;

import br.com.alura.clientelo.dataprocessor.order.OrderDTO;
import br.com.alura.clientelo.report.result.Result;

import java.util.List;

public interface Report {
    Result process(List<OrderDTO> orders, Integer limiter) throws Exception;
}
