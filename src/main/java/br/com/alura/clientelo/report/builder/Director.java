package br.com.alura.clientelo.report.builder;

import br.com.alura.clientelo.report.builder.enums.ReportType;
import br.com.alura.clientelo.report.builder.enums.ResultType;
import br.com.alura.clientelo.report.builder.enums.SourceType;

public class Director {

    public void constructGeneralReport(Builder builder) {
        builder.withSource(SourceType.JSON);
        builder.withType(ReportType.GENERAL_REPORT);
        builder.withLimiter(null);
        builder.withResultType(ResultType.TEXT);
    }

    public void constructLoyalCustomers(Builder builder) {
        builder.withSource(SourceType.JSON);
        builder.withType(ReportType.LOYAL_CUSTOMERS);
        builder.withLimiter(null);
        builder.withResultType(ResultType.TEXT);
    }

    public void constructMostExpensveProductsPerCategory(Builder builder) {
        builder.withSource(SourceType.JSON);
        builder.withType(ReportType.MOST_EXPENSIVE_PRODUCTS_PER_CATEGORY);
        builder.withLimiter(null);
        builder.withResultType(ResultType.TEXT);
    }

    public void constructMostProfitableCustomers(Builder builder) {
        builder.withSource(SourceType.JSON);
        builder.withType(ReportType.MOST_PROFITABLE_CUSTOMERS);
        builder.withLimiter(2);
        builder.withResultType(ResultType.TEXT);
    }

    public void constructsalesPerCategory(Builder builder) {
        builder.withSource(SourceType.JSON);
        builder.withType(ReportType.SALES_PER_CATEGORY);
        builder.withLimiter(null);
        builder.withResultType(ResultType.TEXT);
    }

    public void constructTopSellingProducs(Builder builder) {
        builder.withSource(SourceType.JSON);
        builder.withType(ReportType.TOP_SELLING_PRODUCTS);
        builder.withLimiter(3);
        builder.withResultType(ResultType.TEXT);
    }
}