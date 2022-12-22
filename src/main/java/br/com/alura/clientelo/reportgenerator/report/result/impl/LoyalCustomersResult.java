package br.com.alura.clientelo.reportgenerator.report.result.impl;

import br.com.alura.clientelo.reportgenerator.report.result.Result;

import java.util.LinkedHashMap;

public class LoyalCustomersResult implements Result {

    private final LinkedHashMap<String, Integer> information;

    public LoyalCustomersResult(LinkedHashMap<String, Integer> information) {
        this.information = information;
    }

    @Override
    public String generateText() {
        try {
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
        } catch (Exception e) {
            throw new RuntimeException(String.format("Error while generating text: %s", e));
        }
    }
}
