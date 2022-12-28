package br.com.alura.clientelo.reportgenerator.domain.result.impl;

import br.com.alura.clientelo.reportgenerator.domain.report.impl.TopSellingProducts;
import br.com.alura.clientelo.reportgenerator.domain.result.Result;

import java.util.List;

public class TopSellingProductsResult implements Result {

    private final List<TopSellingProducts.Information> information;

    public TopSellingProductsResult(List<TopSellingProducts.Information> information) {
        this.information = information;
    }

    @Override
    public String generateText() {
        try {
            StringBuilder response = new StringBuilder();
            response.append("##### RELATÓRIO DE PRODUTOS MAIS VENDIDOS ##### \n");
            response.append("\n");

            information.forEach(order -> {
                response.append(String.format("CATEGORIA: %s \n", order.category()));
                response.append(String.format("PRODUTO: %s \n", order.product()));
                response.append(String.format("QUANTIDADE VENDIDA: %s \n", order.quantity()));
                response.append("\n");
            });

            response.append("##### FIM DO RELATÓRIO ##### \n");
            return response.toString();
        } catch (Exception e) {
            throw new RuntimeException(String.format("Error while generating text: %s", e));
        }
    }
}
