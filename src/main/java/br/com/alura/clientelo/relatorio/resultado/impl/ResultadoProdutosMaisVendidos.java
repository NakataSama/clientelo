package br.com.alura.clientelo.relatorio.resultado.impl;

import br.com.alura.clientelo.pedido.Pedido;
import br.com.alura.clientelo.relatorio.impl.ProdutosMaisVendidos;
import br.com.alura.clientelo.relatorio.resultado.Resultado;

import java.util.List;

public class ResultadoProdutosMaisVendidos implements Resultado<ProdutosMaisVendidos> {

    private List<Pedido> resultado;

    public ResultadoProdutosMaisVendidos(List<Pedido> resultado) {
        this.resultado = resultado;
    }

    @Override
    public String gerarRelatorioEmTexto() {

        StringBuilder resposta = new StringBuilder();
        resposta.append("##### RELATÓRIO DE PRODUTOS MAIS VENDIDOS ##### \n");
        resposta.append("\n");

        resultado.forEach(pedido -> {
            resposta.append(String.format("CATEGORIA: %s \n", pedido.getCategoria()));
            resposta.append(String.format("PRODUTO: %s \n", pedido.getProduto()));
            resposta.append(String.format("QUANTIDADE VENDIDA: %s \n", pedido.getQuantidade()));
            resposta.append("\n");
        });

        resposta.append("##### FIM DO RELATÓRIO ##### \n");
        return resposta.toString();
    }
}
