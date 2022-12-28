package br.com.alura.clientelo.reportgenerator.app.builder;

import br.com.alura.clientelo.reportgenerator.app.builder.enums.OutcomeType;
import br.com.alura.clientelo.reportgenerator.app.builder.enums.ReportType;

public interface Builder {

    Builder withFilePath(String filePath);
    Builder withType(ReportType reportType);
    Builder withOutcome(OutcomeType outcomeType);
    Builder withLimiter(Integer limiter);

}
