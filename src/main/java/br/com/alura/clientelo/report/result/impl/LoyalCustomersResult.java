package br.com.alura.clientelo.report.result.impl;

import br.com.alura.clientelo.report.result.Result;

import java.util.LinkedHashMap;

public class LoyalCustomersResult implements Result {

    private final LinkedHashMap<String, Integer> information;

    public LoyalCustomersResult(LinkedHashMap<String, Integer> information) {
        this.information = information;
    }

    @Override
    public String generateText() {

        StringBuilder response = new StringBuilder();
        response.append("##### RELATÓRIO DE CLIENTES FIÉIS ##### \n");
        response.append("\n");

        information.forEach((customer, numberOfOrders) -> {
            response.append(String.format("NOME: %s \n", customer));
            response.append(String.format("Nº DE PEDIDOS: %s \n", numberOfOrders));
            response.append("\n");
        });

        response.append("##### FIM DO RELATÓRIO ##### \n");
        return response.toString();
    }
}
