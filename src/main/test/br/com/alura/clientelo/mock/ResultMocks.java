package br.com.alura.clientelo.mock;

public class ResultMocks {

    public static String getGeneralReportMock() {
        return "##### RELATÓRIO DE VALORES TOTAIS ##### \n" +
                "\n" +
                "TOTAL DE PEDIDOS REALIZADOS: 2\n" +
                "TOTAL DE PRODUTOS VENDIDOS: 2\n" +
                "TOTAL DE CATEGORIAS: 2\n" +
                "MONTANTE DE VENDAS: R$ 6.023,00\n" +
                "PEDIDO MAIS BARATO: R$ 2.500,00 (Sofá 3 lugares)\n" +
                "PEDIDO MAIS CARO: R$ 3.523,00 (Notebook Samsung)\n" +
                "\n" +
                "### FIM DO RELATÓRIO ###\n";
    }

    public static String getLoyalCustomersReportMock() {
        return "##### RELATÓRIO DE CLIENTES FIÉIS ##### \n" +
                "\n" +
                "NOME: ANA \n" +
                "Nº DE PEDIDOS: 2 \n" +
                "\n" +
                "##### FIM DO RELATÓRIO ##### \n";
    }

    public static String getMostExpensiveProductsPerCategoryReportMock() {
        return "##### RELATÓRIO DE PRODUTOS MAIS CAROS POR CATEGORIA ##### \n" +
                "\n" +
                "CATEGORIA: INFORMÁTICA \n" +
                "PRODUTO: Notebook Samsung \n" +
                "PREÇO: R$ 3.523,00 \n" +
                "\n" +
                "CATEGORIA: MÓVEIS \n" +
                "PRODUTO: Sofá 3 lugares \n" +
                "PREÇO: R$ 2.500,00 \n" +
                "\n" +
                "##### FIM DO RELATÓRIO ##### \n";
    }

    public static String getMostProfitableCustomersReportMock() {
        return "##### RELATÓRIO DE CLIENTES MAIS LUCRATIVOS ##### \n" +
                "\n" +
                "NOME: ANA \n" +
                "Nº DE PEDIDOS: 2 \n" +
                "MONTANTE GASTO: R$ 6.023,00 \n" +
                "\n" +
                "##### FIM DO RELATÓRIO ##### \n";
    }

    public static String getSalesPerCategoryReportMock() {
        return "##### RELATÓRIO DE VENDAS POR CATEGORIA ##### \n" +
                "\n" +
                "CATEGORIA: INFORMÁTICA \n" +
                "QUANTIDADE VENDIDA: 1 \n" +
                "QUANTIDADE MONTANTE: R$ 3.523,00 \n" +
                "\n" +
                "CATEGORIA: MÓVEIS \n" +
                "QUANTIDADE VENDIDA: 1 \n" +
                "QUANTIDADE MONTANTE: R$ 2.500,00 \n" +
                "\n" +
                "##### FIM DO RELATÓRIO ##### \n";
    }

    public static String getTopSellingProductsReportMock() {
        return "##### RELATÓRIO DE PRODUTOS MAIS VENDIDOS ##### \n" +
                "\n" +
                "CATEGORIA: INFORMÁTICA \n" +
                "PRODUTO: Notebook Samsung \n" +
                "QUANTIDADE VENDIDA: 1 \n" +
                "\n" +
                "CATEGORIA: MÓVEIS \n" +
                "PRODUTO: Sofá 3 lugares \n" +
                "QUANTIDADE VENDIDA: 1 \n" +
                "\n" +
                "##### FIM DO RELATÓRIO ##### \n";
    }
}
