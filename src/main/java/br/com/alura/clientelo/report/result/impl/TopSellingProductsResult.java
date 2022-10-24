package br.com.alura.clientelo.report.result.impl;

import br.com.alura.clientelo.order.Order;
import br.com.alura.clientelo.report.result.Result;

import java.util.List;

public class TopSellingProductsResult implements Result {

    private final List<Order> result;

    public TopSellingProductsResult(List<Order> result) {
        this.result = result;
    }

    @Override
    public String generateText() {

        StringBuilder response = new StringBuilder();
        response.append("##### RELATÓRIO DE PRODUTOS MAIS VENDIDOS ##### \n");
        response.append("\n");

        result.forEach(order -> {
            response.append(String.format("CATEGORIA: %s \n", order.getCategory()));
            response.append(String.format("PRODUTO: %s \n", order.getProduct()));
            response.append(String.format("QUANTIDADE VENDIDA: %s \n", order.getQuantity()));
            response.append("\n");
        });

        response.append("##### FIM DO RELATÓRIO ##### \n");
        return response.toString();
    }
}