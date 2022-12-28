package br.com.alura.clientelo.reportgenerator.app.builder;

import br.com.alura.clientelo.reportgenerator.app.builder.enums.OutcomeType;
import br.com.alura.clientelo.reportgenerator.app.builder.enums.ReportType;
import br.com.alura.clientelo.reportgenerator.infra.fileprocessor.FileProcessor;
import br.com.alura.clientelo.reportgenerator.domain.report.dto.ReportOrderDTO;

import java.util.List;

public class ReportBuilder implements Builder {

    private String filePath = "";
    private ReportType reportType = ReportType.GENERAL_REPORT;
    private OutcomeType outcomeType = OutcomeType.CONSOLE;
    private Integer limiter = null;

    @Override
    public Builder withFilePath(String filePath) {
        this.filePath = filePath;
        return this;
    }

    @Override
    public Builder withType(ReportType reportType) {
        this.reportType = reportType;
        return this;
    }

    @Override
    public Builder withOutcome(OutcomeType outcomeType) {
        this.outcomeType = outcomeType;
        return this;
    }

    @Override
    public Builder withLimiter(Integer limiter) {
        this.limiter = limiter;
        return this;
    }

    public void buildAndExecute() {
        try {
            List<ReportOrderDTO> orders = new FileProcessor().processFile(filePath);
            if(orders.size() != 0)
                outcomeType.apply(reportType.getInstance().process(orders, limiter));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
