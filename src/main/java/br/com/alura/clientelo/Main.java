package br.com.alura.clientelo;

import br.com.alura.clientelo.report.builder.Director;
import br.com.alura.clientelo.report.builder.ReportBuilder;

public class Main {

    public static void main(String[] args) {
        Director director = new Director();
        ReportBuilder builder = new ReportBuilder();

        director.constructGeneralReport(builder);
        builder.buildWithOutcome();

        director.constructLoyalCustomers(builder);
        builder.buildWithOutcome();

        director.constructMostExpensveProductsPerCategory(builder);
        builder.buildWithOutcome();

        director.constructMostProfitableCustomers(builder);
        builder.buildWithOutcome();

        director.constructSalesPerCategory(builder);
        builder.buildWithOutcome();

        director.constructTopSellingProducs(builder);
        builder.buildWithOutcome();
    }
}