package br.com.alura.clientelo.report.builder;

import br.com.alura.clientelo.report.builder.enums.ReportType;
import br.com.alura.clientelo.report.builder.enums.SourceType;
import br.com.alura.clientelo.report.builder.enums.OutcomeType;

public interface Builder {

    void withSource(SourceType sourceType);
    void withType(ReportType reportType);
    void withOutcome(OutcomeType outcomeType);
    void withLimiter(Integer limiter);

}
