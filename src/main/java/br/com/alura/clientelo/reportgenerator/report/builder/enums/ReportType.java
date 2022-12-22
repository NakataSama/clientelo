package br.com.alura.clientelo.reportgenerator.report.builder.enums;

import br.com.alura.clientelo.reportgenerator.report.Report;
import br.com.alura.clientelo.reportgenerator.report.impl.*;
import br.com.alura.clientelo.report.impl.*;

public enum ReportType implements ReportTypeInterface<Report> {
    GENERAL_REPORT {
        @Override
        public Report getInstance() {
            return new GeneralReport();
        }
    },
    LOYAL_CUSTOMERS {
        @Override
        public Report getInstance() {
            return new LoyalCustomers();
        }
    },
    MOST_EXPENSIVE_PRODUCTS_PER_CATEGORY{
        @Override
        public Report getInstance() {
            return new MostExpensiveProductsPerCategory();
        }
    },
    MOST_PROFITABLE_CUSTOMERS {
        @Override
        public Report getInstance() {
            return new MostProfitableCustomers();
        }
    },
    SALES_PER_CATEGORY {
        @Override
        public Report getInstance() {
            return new SalesPerCategory();
        }
    },
    TOP_SELLING_PRODUCTS {
        @Override
        public Report getInstance() {
            return new TopSellingProducts();
        }
    }
}
