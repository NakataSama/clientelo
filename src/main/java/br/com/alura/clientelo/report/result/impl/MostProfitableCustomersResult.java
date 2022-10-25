package br.com.alura.clientelo.report.result.impl;

import br.com.alura.clientelo.report.impl.MostProfitableCustomers;
import br.com.alura.clientelo.report.result.Result;
import br.com.alura.clientelo.util.CurrencyFormatter;

import java.util.LinkedHashMap;

public class MostProfitableCustomersResult implements Result {

    private final LinkedHashMap<String, MostProfitableCustomers.Information> information;

    public MostProfitableCustomersResult(LinkedHashMap<String, MostProfitableCustomers.Information> information) {
        this.information = information;
    }

    @Override
    public String generateText() {

        StringBuilder response = new StringBuilder();
        response.append("##### RELATÓRIO DE CLIENTES MAIS LUCRATIVOS ##### \n");
        response.append("\n");

        information.forEach((customer, information) -> {
            response.append(String.format("NOME: %s \n", customer));
            response.append(String.format("Nº DE PEDIDOS: %s \n", information.numberOfOrders()));
            response.append(String.format("MONTANTE GASTO: %s \n", CurrencyFormatter.TO_BRAZIL_REAL(information.totalAmount())));
            response.append("\n");
        });

        response.append("##### FIM DO RELATÓRIO ##### \n");
        return response.toString();
    }
}
