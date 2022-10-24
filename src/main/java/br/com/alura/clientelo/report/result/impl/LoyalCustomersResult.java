package br.com.alura.clientelo.report.result.impl;

import br.com.alura.clientelo.report.impl.LoyalCustomers;
import br.com.alura.clientelo.report.result.Result;

import java.util.LinkedHashMap;

public class LoyalCustomersResult implements Result {

    private final LinkedHashMap<String, Integer> result;

    public LoyalCustomersResult(LinkedHashMap<String, Integer> result) {
        this.result = result;
    }

    @Override
    public String generateText() {

        StringBuilder response = new StringBuilder();
        response.append("##### RELATÓRIO DE CLIENTES FIÉIS ##### \n");
        response.append("\n");

        result.forEach((customer, numberOfOrders) -> {
            response.append(String.format("NOME: %s \n", customer));
            response.append(String.format("Nº DE PEDIDOS: %s \n", numberOfOrders));
            response.append("\n");
        });

        response.append("##### FIM DO RELATÓRIO ##### \n");
        return response.toString();
    }
}
