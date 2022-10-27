package br.com.alura.clientelo.report.builder;

import br.com.alura.clientelo.report.builder.enums.OutcomeType;
import br.com.alura.clientelo.report.builder.enums.ReportType;

public interface Builder {

    void withFilePath(String filePath);
    void withType(ReportType reportType);
    void withOutcome(OutcomeType outcomeType);
    void withLimiter(Integer limiter);

}
