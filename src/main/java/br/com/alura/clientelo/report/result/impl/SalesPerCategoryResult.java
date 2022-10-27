package br.com.alura.clientelo.report.result.impl;

import br.com.alura.clientelo.report.impl.SalesPerCategory;
import br.com.alura.clientelo.report.result.Result;
import br.com.alura.clientelo.util.CurrencyFormatter;

import java.util.LinkedHashMap;

public class SalesPerCategoryResult implements Result {

    LinkedHashMap<String, SalesPerCategory.Information> information;

    public SalesPerCategoryResult(LinkedHashMap<String, SalesPerCategory.Information> information) {
        this.information = information;
    }

    @Override
    public String generateText() {
        try {
            StringBuilder response = new StringBuilder();
            response.append("##### RELATÓRIO DE VENDAS POR CATEGORIA ##### \n");
            response.append("\n");

            information.forEach((category, information) -> {

                response.append(String.format("CATEGORIA: %s \n", category));
                response.append(String.format("QUANTIDADE VENDIDA: %s \n", information.numberOfOrders()));
                response.append(String.format("QUANTIDADE MONTANTE: %s \n", CurrencyFormatter.TO_BRAZIL_REAL(information.totalAmount())));
                response.append("\n");
            });

            response.append("##### FIM DO RELATÓRIO ##### \n");
            return response.toString();
        } catch (Exception e) {
            throw new RuntimeException(String.format("Error while generating text: %s", e));
        }
    }
}
