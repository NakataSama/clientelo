package br.com.alura.clientelo.relatorio.resultado.impl;

import br.com.alura.clientelo.relatorio.impl.ValoresTotais;
import br.com.alura.clientelo.relatorio.resultado.Resultado;
import br.com.alura.clientelo.util.FormatadorDeMoeda;

import java.math.BigDecimal;

public class ResultadoValoresTotais implements Resultado<ValoresTotais> {

    ValoresTotais.Informacao resultado;
    public ResultadoValoresTotais(ValoresTotais.Informacao resultado) {
        this.resultado = resultado;
    }

    @Override
    public String gerarRelatorioEmTexto() {

        StringBuilder resposta = new StringBuilder();
        resposta.append("##### RELATÓRIO DE VALORES TOTAIS ##### \n");
        resposta.append("\n");

        resposta.append(String.format("TOTAL DE PEDIDOS REALIZADOS: %s\n", resultado.getTotalDePedidosRealizados()));
        resposta.append(String.format("TOTAL DE PRODUTOS VENDIDOS: %s\n", resultado.getTotalDeProdutosVendidos()));
        resposta.append(String.format("TOTAL DE CATEGORIAS: %s\n", resultado.getTotalDeCategorias()));
        resposta.append(String.format("MONTANTE DE VENDAS: %s\n", FormatadorDeMoeda.CONVERTE_PARA_REAL(resultado.getMontanteDeVendas())));

        String produtoMaisBarato = resultado.getPedidoMaisBarato().entrySet().iterator().next().getKey();
        BigDecimal valorMaisBarato = resultado.getPedidoMaisBarato().entrySet().iterator().next().getValue();
        resposta.append(String.format("PEDIDO MAIS BARATO: %s (%s)\n", FormatadorDeMoeda.CONVERTE_PARA_REAL(valorMaisBarato),  produtoMaisBarato));

        String produtoMaisCaro = resultado.getPedidoMaisCaro().entrySet().iterator().next().getKey();
        BigDecimal valorMaisCaro = resultado.getPedidoMaisCaro().entrySet().iterator().next().getValue();
        resposta.append(String.format("PEDIDO MAIS CARO: %s (%s)\n", FormatadorDeMoeda.CONVERTE_PARA_REAL(valorMaisCaro), produtoMaisCaro));
        resposta.append("\n");
        resposta.append("### FIM DO RELATÓRIO ###");
        resposta.append("\n");

        return resposta.toString();
    }
}
