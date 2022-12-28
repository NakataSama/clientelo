package br.com.alura.clientelo.reportgenerator.domain.result.impl;

import br.com.alura.clientelo.reportgenerator.domain.report.impl.MostExpensiveProductsPerCategory;
import br.com.alura.clientelo.reportgenerator.domain.result.Result;
import br.com.alura.clientelo.util.CurrencyFormatter;

import java.util.LinkedHashMap;

public class MostExpensiveProductsPerCategoryResult implements Result {

    private final LinkedHashMap<String, MostExpensiveProductsPerCategory.Information> information;
    public MostExpensiveProductsPerCategoryResult(LinkedHashMap<String, MostExpensiveProductsPerCategory.Information> information) {
        this.information = information;
    }

    @Override
    public String generateText() {
        try {
            StringBuilder response = new StringBuilder();
            response.append("##### RELATÓRIO DE PRODUTOS MAIS CAROS POR CATEGORIA ##### \n");
            response.append("\n");

            information.forEach((category, information) -> {

                response.append(String.format("CATEGORIA: %s \n", category));
                response.append(String.format("PRODUTO: %s \n", information.product()));
                response.append(String.format("PREÇO: %s \n", CurrencyFormatter.TO_BRAZIL_REAL(information.price())));
                response.append("\n");
            });

            response.append("##### FIM DO RELATÓRIO ##### \n");
            return response.toString();
        } catch (Exception e) {
            throw new RuntimeException(String.format("Error while generating text: %s", e));
        }
    }
}
