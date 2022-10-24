package br.com.alura.clientelo.report.builder;

import br.com.alura.clientelo.report.builder.enums.ReportType;
import br.com.alura.clientelo.report.builder.enums.ResultType;
import br.com.alura.clientelo.report.builder.enums.SourceType;

public interface Builder {

    void withSource(SourceType sourceType);
    void withType(ReportType reportType);
    void withResultType(ResultType resultType);
    void withLimiter(Integer limiter);

}
