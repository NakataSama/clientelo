package br.com.alura.clientelo.reportgenerator.report;

import br.com.alura.clientelo.reportgenerator.report.result.Result;

import java.util.List;

public interface Report {
    Result process(List<ReportOrderDTO> orders, Integer limiter) throws Exception;
}
