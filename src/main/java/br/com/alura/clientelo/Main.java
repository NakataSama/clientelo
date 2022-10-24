package br.com.alura.clientelo;

import br.com.alura.clientelo.report.builder.Director;
import br.com.alura.clientelo.report.builder.ReportBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        Director director = new Director();
        ReportBuilder builder = new ReportBuilder();

        director.constructGeneralReport(builder);
        System.out.println(builder.build());

        director.constructLoyalCustomers(builder);
        System.out.println(builder.build());

        director.constructMostExpensveProductsPerCategory(builder);
        System.out.println(builder.build());

        director.constructMostProfitableCustomers(builder);
        System.out.println(builder.build());

        director.constructsalesPerCategory(builder);
        System.out.println(builder.build());

        director.constructTopSellingProducs(builder);
        System.out.println(builder.build());
    }
}

