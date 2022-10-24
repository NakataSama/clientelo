package br.com.alura.clientelo.report.builder;

import br.com.alura.clientelo.dataprocessor.DataProcessor;
import br.com.alura.clientelo.order.Order;
import br.com.alura.clientelo.report.builder.enums.ReportType;
import br.com.alura.clientelo.report.builder.enums.ResultType;
import br.com.alura.clientelo.report.builder.enums.SourceType;
import br.com.alura.clientelo.report.impl.*;

import java.util.ArrayList;
import java.util.List;

public class ReportBuilder implements Builder {

    private SourceType sourceType;
    private String filePath;
    private ReportType reportType;
    private ResultType resultType;
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
    public void withResultType(ResultType resultType) {
        this.resultType = resultType;
    }

    @Override
    public void withLimiter(Integer limiter) {
        this.limiter = limiter;
    }

    public Object build() {

        List<Order> orders = new ArrayList<>();

        if(sourceType == SourceType.JSON) orders = DataProcessor.processJson(ClassLoader.getSystemResource(filePath));
        if(sourceType == SourceType.CSV) orders = DataProcessor.processCsv(ClassLoader.getSystemResource(filePath));

        switch(reportType) {
            case GENERAL_REPORT -> {
                if (resultType == ResultType.TEXT)
                    return ResultType.TEXT.apply(new GeneralReport().process(orders, limiter));
            }
            case LOYAL_CUSTOMERS -> {
                if (resultType == ResultType.TEXT)
                    return ResultType.TEXT.apply(new LoyalCustomers().process(orders, limiter));
            }
            case MOST_EXPENSIVE_PRODUCTS_PER_CATEGORY -> {
                if (resultType == ResultType.TEXT)
                    return ResultType.TEXT.apply(new MostExpensiveProductsPerCategory().process(orders, limiter));
            }
            case MOST_PROFITABLE_CUSTOMERS -> {
                if (resultType == ResultType.TEXT)
                    return  ResultType.TEXT.apply(new MostProfitableCustomers().process(orders, limiter));
            }
            case SALES_PER_CATEGORY -> {
                if (resultType == ResultType.TEXT)
                    return  ResultType.TEXT.apply(new SalesPerCategory().process(orders, limiter));
            }
            case TOP_SELLING_PRODUCTS -> {
                if (resultType == ResultType.TEXT)
                    return  ResultType.TEXT.apply(new TopSellingProducts().process(orders, limiter));
            }
        }

        return Boolean.FALSE;
    }
}
