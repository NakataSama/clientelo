package br.com.alura.clientelo.report.result.impl;

import br.com.alura.clientelo.report.impl.GeneralReport;
import br.com.alura.clientelo.report.result.Result;
import br.com.alura.clientelo.util.CurrencyFormatter;

import java.math.BigDecimal;

public class GeneralReportResult implements Result {

    private final GeneralReport.Information information;
    public GeneralReportResult(GeneralReport.Information information) {
        this.information = information;
    }

    @Override
    public String generateText() {

        StringBuilder response = new StringBuilder();
        response.append("##### RELATÓRIO DE VALORES TOTAIS ##### \n");
        response.append("\n");

        response.append(String.format("TOTAL DE PEDIDOS REALIZADOS: %s\n", information.numberOfOrders()));
        response.append(String.format("TOTAL DE PRODUTOS VENDIDOS: %s\n", information.productsSold()));
        response.append(String.format("TOTAL DE CATEGORIAS: %s\n", information.categories()));
        response.append(String.format("MONTANTE DE VENDAS: %s\n", CurrencyFormatter.TO_BRAZIL_REAL(information.totalAmount())));

        String leastProfitableOrderProduct = information.leastProfitableOrder().entrySet().stream().findFirst().get().getKey();
        BigDecimal leastProfitableOrderValue = information.leastProfitableOrder().entrySet().stream().findFirst().get().getValue();
        response.append(String.format("PEDIDO MAIS BARATO: %s (%s)\n", CurrencyFormatter.TO_BRAZIL_REAL(leastProfitableOrderValue),  leastProfitableOrderProduct));

        String mostProfitableOrderProduct = information.mostProfitableOrder().entrySet().stream().findFirst().get().getKey();
        BigDecimal mostProfitableOrderValue = information.mostProfitableOrder().entrySet().stream().findFirst().get().getValue();
        response.append(String.format("PEDIDO MAIS CARO: %s (%s)\n", CurrencyFormatter.TO_BRAZIL_REAL(mostProfitableOrderValue), mostProfitableOrderProduct));
        response.append("\n");
        response.append("### FIM DO RELATÓRIO ###");
        response.append("\n");

        return response.toString();
    }
}
