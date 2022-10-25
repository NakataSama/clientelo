package br.com.alura.clientelo.report.builder;

import br.com.alura.clientelo.dataprocessor.DataProcessor;
import br.com.alura.clientelo.order.Order;
import br.com.alura.clientelo.report.Report;
import br.com.alura.clientelo.report.builder.enums.ReportType;
import br.com.alura.clientelo.report.builder.enums.OutcomeType;
import br.com.alura.clientelo.report.builder.enums.SourceType;

import java.util.ArrayList;
import java.util.List;

public class ReportBuilder implements Builder {

    private SourceType sourceType;
    private String filePath;
    private ReportType reportType;
    private OutcomeType outcomeType;
    private Integer limiter = null;

    @Override
    public void withSource(SourceType sourceType) {
        this.sourceType = sourceType;
        this.filePath = String.format("pedidos%s", sourceType.getFileExtension());
    }

    @Override
    public void withType(ReportType reportType) {
        this.reportType = reportType;
    }

    @Override
    public void withOutcome(OutcomeType outcomeType) {
        this.outcomeType = outcomeType;
    }

    @Override
    public void withLimiter(Integer limiter) {
        this.limiter = limiter;
    }

    public Report build() {
        return reportType.getInstance();
    }

    public void buildWithOutcome() {
        List<Order> orders = new ArrayList<>();

        if(sourceType == SourceType.JSON) orders = DataProcessor.processJson(ClassLoader.getSystemResource(filePath));
        if(sourceType == SourceType.CSV) orders = DataProcessor.processCsv(ClassLoader.getSystemResource(filePath));

        outcomeType.apply(reportType.getInstance().process(orders, limiter));
    }
}
