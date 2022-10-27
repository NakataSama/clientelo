package br.com.alura.clientelo.report.builder;

import br.com.alura.clientelo.dataprocessor.DataProcessor;
import br.com.alura.clientelo.order.Order;
import br.com.alura.clientelo.report.builder.enums.OutcomeType;
import br.com.alura.clientelo.report.builder.enums.ReportType;

import java.util.List;

public class ReportBuilder implements Builder {

    private String filePath = null;
    private ReportType reportType = null;
    private OutcomeType outcomeType = null;
    private Integer limiter = null;

    @Override
    public void withFilePath(String filePath) {
        this.filePath = filePath;
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

    public void buildWithOutcome() {
        try {
            List<Order> orders = new DataProcessor().processFile(filePath);
            if(orders.size() != 0)
                outcomeType.apply(reportType.getInstance().process(orders, limiter));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
