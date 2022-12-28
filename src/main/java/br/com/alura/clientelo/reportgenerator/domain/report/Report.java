package br.com.alura.clientelo.reportgenerator.domain.report;

import br.com.alura.clientelo.reportgenerator.domain.report.dto.ReportOrderDTO;
import br.com.alura.clientelo.reportgenerator.domain.result.Result;

import java.util.List;

public interface Report {
    Result process(List<ReportOrderDTO> orders, Integer limiter) throws Exception;
}
