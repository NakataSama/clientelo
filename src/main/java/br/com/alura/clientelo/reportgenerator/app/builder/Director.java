package br.com.alura.clientelo.reportgenerator.app.builder;

import br.com.alura.clientelo.reportgenerator.app.builder.enums.OutcomeType;
import br.com.alura.clientelo.reportgenerator.app.builder.enums.ReportType;

public class Director {

    public void constructGeneralReport(Builder builder) {
        builder.withFilePath("pedidos.json")
                .withType(ReportType.GENERAL_REPORT)
                .withLimiter(null)
                .withOutcome(OutcomeType.CONSOLE);
    }

    public void constructLoyalCustomers(Builder builder) {
        builder.withFilePath("pedidos.json")
                .withType(ReportType.LOYAL_CUSTOMERS)
                .withLimiter(null)
                .withOutcome(OutcomeType.CONSOLE);
    }

    public void constructMostExpensveProductsPerCategory(Builder builder) {
        builder.withFilePath("pedidos.json")
                .withType(ReportType.MOST_EXPENSIVE_PRODUCTS_PER_CATEGORY)
                .withLimiter(null)
                .withOutcome(OutcomeType.CONSOLE);
    }

    public void constructMostProfitableCustomers(Builder builder) {
        builder.withFilePath("pedidos.json")
                .withType(ReportType.MOST_PROFITABLE_CUSTOMERS)
                .withLimiter(3)
                .withOutcome(OutcomeType.CONSOLE);
    }

    public void constructSalesPerCategory(Builder builder) {
        builder.withFilePath("pedidos.json")
                .withType(ReportType.SALES_PER_CATEGORY)
                .withLimiter(null)
                .withOutcome(OutcomeType.CONSOLE);
    }

    public void constructTopSellingProducs(Builder builder) {
        builder.withFilePath("pedidos.json")
                .withType(ReportType.TOP_SELLING_PRODUCTS)
                .withLimiter(3)
                .withOutcome(OutcomeType.CONSOLE);
    }
}
