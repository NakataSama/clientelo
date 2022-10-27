package br.com.alura.clientelo;

import br.com.alura.clientelo.report.builder.Director;
import br.com.alura.clientelo.report.builder.ReportBuilder;

public class Main {

    public static void main(String[] args) {
        Director director = new Director();
        ReportBuilder builder = new ReportBuilder();

        director.constructGeneralReport(builder);
        builder.buildAndExecute();

        director.constructLoyalCustomers(builder);
        builder.buildAndExecute();

        director.constructMostExpensveProductsPerCategory(builder);
        builder.buildAndExecute();

        director.constructMostProfitableCustomers(builder);
        builder.buildAndExecute();

        director.constructSalesPerCategory(builder);
        builder.buildAndExecute();

        director.constructTopSellingProducs(builder);
        builder.buildAndExecute();
    }
}