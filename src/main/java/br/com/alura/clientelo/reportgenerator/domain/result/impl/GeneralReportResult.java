package br.com.alura.clientelo.reportgenerator.domain.result.impl;

import br.com.alura.clientelo.reportgenerator.domain.report.impl.GeneralReport;
import br.com.alura.clientelo.reportgenerator.domain.result.Result;
import br.com.alura.clientelo.util.CurrencyFormatter;

public class GeneralReportResult implements Result {

    private final GeneralReport.Information information;
    public GeneralReportResult(GeneralReport.Information information) {
        this.information = information;
    }

    @Override
    public String generateText() {
        try {
            StringBuilder response = new StringBuilder();
            response.append("##### RELATÓRIO DE VALORES TOTAIS ##### \n");
            response.append("\n");

            response.append(String.format("TOTAL DE PEDIDOS REALIZADOS: %s\n", information.numberOfOrders()));
            response.append(String.format("TOTAL DE PRODUTOS VENDIDOS: %s\n", information.productsSold()));
            response.append(String.format("TOTAL DE CATEGORIAS: %s\n", information.categories()));
            response.append(String.format("MONTANTE DE VENDAS: %s\n", CurrencyFormatter.TO_BRAZIL_REAL(information.totalAmount())));

            information.leastProfitableOrder().entrySet().stream()
                    .findFirst()
                    .ifPresentOrElse(
                            map -> response.append(String.format("PEDIDO MAIS BARATO: %s (%s)\n", CurrencyFormatter.TO_BRAZIL_REAL(map.getValue()), map.getKey())),
                            () -> response.append("Sem pedido")
                    );

            information.mostProfitableOrder().entrySet().stream()
                    .findFirst()
                    .ifPresentOrElse(
                            map -> response.append(String.format("PEDIDO MAIS CARO: %s (%s)\n\n", CurrencyFormatter.TO_BRAZIL_REAL(map.getValue()), map.getKey())),
                            () -> response.append("Sem pedido")
                    );

            response.append("### FIM DO RELATÓRIO ###");
            response.append("\n");
            return response.toString();
        } catch (Exception e) {
            throw new RuntimeException(String.format("Error while generating text: %s", e));
        }
    }
}
