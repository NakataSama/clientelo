package br.com.alura.clientelo.report.builder;

import br.com.alura.clientelo.report.builder.enums.OutcomeType;
import br.com.alura.clientelo.report.builder.enums.ReportType;

public class Director {

    public void constructGeneralReport(Builder builder) {
        builder.withFilePath("pedidos.json");
        builder.withType(ReportType.GENERAL_REPORT);
        builder.withLimiter(null);
        builder.withOutcome(OutcomeType.CONSOLE);
    }

    public void constructLoyalCustomers(Builder builder) {
        builder.withFilePath("pedidos.json");
        builder.withType(ReportType.LOYAL_CUSTOMERS);
        builder.withLimiter(null);
        builder.withOutcome(OutcomeType.CONSOLE);
    }

    public void constructMostExpensveProductsPerCategory(Builder builder) {
        builder.withFilePath("pedidos.json");
        builder.withType(ReportType.MOST_EXPENSIVE_PRODUCTS_PER_CATEGORY);
        builder.withLimiter(null);
        builder.withOutcome(OutcomeType.CONSOLE);
    }

    public void constructMostProfitableCustomers(Builder builder) {
        builder.withFilePath("pedidos.json");
        builder.withType(ReportType.MOST_PROFITABLE_CUSTOMERS);
        builder.withLimiter(2);
        builder.withOutcome(OutcomeType.CONSOLE);
    }

    public void constructSalesPerCategory(Builder builder) {
        builder.withFilePath("pedidos.json");
        builder.withType(ReportType.SALES_PER_CATEGORY);
        builder.withLimiter(null);
        builder.withOutcome(OutcomeType.CONSOLE);
    }

    public void constructTopSellingProducs(Builder builder) {
        builder.withFilePath("pedidos.json");
        builder.withType(ReportType.TOP_SELLING_PRODUCTS);
        builder.withLimiter(3);
        builder.withOutcome(OutcomeType.CONSOLE);
    }
}
