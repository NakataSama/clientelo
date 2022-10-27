package br.com.alura.clientelo.report.builder;

import br.com.alura.clientelo.report.builder.enums.OutcomeType;
import br.com.alura.clientelo.report.builder.enums.ReportType;

public interface Builder {

    Builder withFilePath(String filePath);
    Builder withType(ReportType reportType);
    Builder withOutcome(OutcomeType outcomeType);
    Builder withLimiter(Integer limiter);

}
