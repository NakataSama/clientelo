package br.com.alura.clientelo;

import br.com.alura.clientelo.order.Order;
import br.com.alura.clientelo.dataprocessor.DataProcessor;
import br.com.alura.clientelo.report.impl.*;
import br.com.alura.clientelo.report.result.impl.*;
import br.com.alura.clientelo.report.result.impl.SalesPerCategoryResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
//        List<Order> orders = DataProcessor.processCsv("pedidos.csv");
        List<Order> orders = DataProcessor.processJson("pedidos.json");

        GeneralReport generalReport = new GeneralReport();
        SalesPerCategory salesPerCategory = new SalesPerCategory();
        TopSellingProducts topSellingProducts = new TopSellingProducts();
        MostExpensiveProductsPerCategory mostExpensiveProductsPerCategory = new MostExpensiveProductsPerCategory();
        LoyalCustomers loyalCustomers = new LoyalCustomers();
        MostProfitableCustomers mostProfitableCustomers = new MostProfitableCustomers();

        GeneralReportResult generalReportResult = generalReport.process(orders);
        System.out.println(generalReportResult.generateText());

        SalesPerCategoryResult salesPerCategoryResult = salesPerCategory.process(orders);
        System.out.println(salesPerCategoryResult.generateText());

        TopSellingProductsResult topSellingProductsResult = topSellingProducts.process(orders);
        System.out.println(topSellingProductsResult.generateText());

        MostExpensiveProductsPerCategoryResult mostExpensiveProductsPerCategoryResult = mostExpensiveProductsPerCategory.process(orders);
        System.out.println(mostExpensiveProductsPerCategoryResult.generateText());

        LoyalCustomersResult loyalCustomersResult = loyalCustomers.process(orders);
        System.out.println(loyalCustomersResult.generateText());

        MostProfitableCustomersResult mostProfitableCustomersResult = mostProfitableCustomers.process(orders);
        System.out.println(mostProfitableCustomersResult.generateText());
    }
}

