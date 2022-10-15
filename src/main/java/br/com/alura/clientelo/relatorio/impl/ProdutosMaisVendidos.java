package br.com.alura.clientelo.relatorio.impl;

import br.com.alura.clientelo.pedido.Pedido;
import br.com.alura.clientelo.relatorio.Relatorio;
import br.com.alura.clientelo.relatorio.resultado.impl.ResultadoProdutosMaisVendidos;

import java.util.Comparator;
import java.util.List;

public class ProdutosMaisVendidos implements Relatorio {
    @Override
    public ResultadoProdutosMaisVendidos processar(List<Pedido> pedidos) {

        pedidos = pedidos.stream()
                .sorted(Comparator.comparing(Pedido::getQuantidade).reversed())
                .limit(3)
                .toList();

        return new ResultadoProdutosMaisVendidos(pedidos);
    }
}
