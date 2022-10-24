package br.com.alura.clientelo.report.result.impl;

import br.com.alura.clientelo.report.result.Result;
import br.com.alura.clientelo.util.CurrencyFormatter;

import java.util.LinkedHashMap;

public class SalesPerCategoryResult implements Result<br.com.alura.clientelo.report.impl.SalesPerCategory> {

    LinkedHashMap<String, br.com.alura.clientelo.report.impl.SalesPerCategory.Information> result;

    public SalesPerCategoryResult(LinkedHashMap<String, br.com.alura.clientelo.report.impl.SalesPerCategory.Information> result) {
        this.result = result;
    }

    @Override
    public String generateText() {

        StringBuilder response = new StringBuilder();
        response.append("##### RELATÓRIO DE VENDAS POR CATEGORIA ##### \n");
        response.append("\n");

        result.forEach((category, information) -> {

            response.append(String.format("CATEGORIA: %s \n", category));
            response.append(String.format("QUANTIDADE VENDIDA: %s \n", information.numberOfOrders()));
            response.append(String.format("QUANTIDADE MONTANTE: %s \n", CurrencyFormatter.TO_BRAZIL_REAL(information.totalAmount())));
            response.append("\n");
        });

        response.append("##### FIM DO RELATÓRIO ##### \n");
        return response.toString();
    }
}
