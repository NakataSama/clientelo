package br.com.alura.clientelo.relatorio.impl;

import br.com.alura.clientelo.pedido.Pedido;
import br.com.alura.clientelo.relatorio.Relatorio;
import br.com.alura.clientelo.relatorio.resultado.impl.ResultadoProdutosMaisCarosPorCategoria;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Stream;

public class ProdutosMaisCarosPorCategoria implements Relatorio {

    public static class Informacao {

        private final String produto;
        private final BigDecimal preco;

        public Informacao(String produto, BigDecimal preco) {
            this.produto = produto;
            this.preco = preco;
        }

        public String getProduto() {
            return produto;
        }

        public BigDecimal getPreco() {
            return preco;
        }
    }

    @Override
    public ResultadoProdutosMaisCarosPorCategoria processar(List<Pedido> pedidos) {

        LinkedHashMap<String, Informacao> resultado = new LinkedHashMap<>();

        Stream<String> categorias = pedidos.stream()
                .map(Pedido::getCategoria)
                .distinct()
                .sorted();

        categorias.forEach(categoria -> {

            List<Pedido> pedidosFiltrados = pedidos.stream().filter(pedido -> pedido.getCategoria().equals(categoria))
                    .sorted(Comparator.comparing(Pedido::getPreco).reversed()).toList();

            String produto = pedidosFiltrados.stream()
                    .map(Pedido::getProduto)
                    .findFirst().orElse("Sem produto");

            BigDecimal preco = pedidosFiltrados.stream()
                    .map(Pedido::getPreco)
                    .findFirst().orElse(BigDecimal.ZERO);

            resultado.put(categoria, new Informacao(produto, preco));
        });

        return new ResultadoProdutosMaisCarosPorCategoria(resultado);
    }
}
