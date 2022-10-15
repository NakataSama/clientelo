package br.com.alura.clientelo.relatorio.impl;

import br.com.alura.clientelo.pedido.Pedido;
import br.com.alura.clientelo.relatorio.Relatorio;
import br.com.alura.clientelo.relatorio.resultado.impl.ResultadoValoresTotais;

import java.math.BigDecimal;
import java.util.*;

public class ValoresTotais implements Relatorio {

    Comparator<Pedido> comparadorDeMontante = (o1, o2) -> o2.getPreco().multiply(BigDecimal.valueOf(o2.getQuantidade()))
            .compareTo(o1.getPreco().multiply(BigDecimal.valueOf(o1.getQuantidade())));

    public static class Informacao {

        private final Integer totalDePedidosRealizados;
        private final Integer totalDeProdutosVendidos;
        private final Integer totalDeCategorias;
        private final BigDecimal montanteDeVendas;
        private final Map<String, BigDecimal> pedidoMaisBarato;
        private final Map<String, BigDecimal> pedidoMaisCaro;

        public Informacao(Integer totalDePedidosRealizados, Integer totalDeProdutosVendidos, Integer totalDeCategorias, BigDecimal montanteDeVendas, Map<String, BigDecimal> pedidoMaisBarato, Map<String, BigDecimal> pedidoMaisCaro) {
            this.totalDePedidosRealizados = totalDePedidosRealizados;
            this.totalDeProdutosVendidos = totalDeProdutosVendidos;
            this.totalDeCategorias = totalDeCategorias;
            this.montanteDeVendas = montanteDeVendas;
            this.pedidoMaisBarato = pedidoMaisBarato;
            this.pedidoMaisCaro = pedidoMaisCaro;
        }

        public Integer getTotalDePedidosRealizados() {
            return totalDePedidosRealizados;
        }

        public Integer getTotalDeProdutosVendidos() {
            return totalDeProdutosVendidos;
        }

        public Integer getTotalDeCategorias() {
            return totalDeCategorias;
        }

        public BigDecimal getMontanteDeVendas() {
            return montanteDeVendas;
        }

        public Map<String, BigDecimal> getPedidoMaisBarato() {
            return pedidoMaisBarato;
        }

        public Map<String, BigDecimal> getPedidoMaisCaro() {
            return pedidoMaisCaro;
        }
    }

    @Override
    public ResultadoValoresTotais processar(List<Pedido> pedidos) {

        Integer totalPedidosRealizados = pedidos.size();

        Integer totalProdutosVendidos = pedidos.stream()
                .map(Pedido::getQuantidade)
                .reduce(0, Integer::sum);

        Integer totalDeCategorias = Math.toIntExact(pedidos.stream()
                .map(Pedido::getCategoria)
                .distinct()
                .count());

        BigDecimal montante = pedidos.stream()
                .map(pedido -> pedido.getPreco().multiply(BigDecimal.valueOf(pedido.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);


        Map<String, BigDecimal> pedidoMaisBarato = pedidos.stream()
                .min(comparadorDeMontante)
                .map(pedido -> {
                    Map<String, BigDecimal> produtoMontanteMap = new HashMap<>();
                    produtoMontanteMap.put(pedido.getProduto(), pedido.getPreco());
                    return produtoMontanteMap;
                }).orElse(null);

        Map<String, BigDecimal> pedidoMaisCaro = pedidos.stream()
                .max(comparadorDeMontante)
                .map(pedido -> {
                    Map<String, BigDecimal> produtoMontanteMap = new HashMap<>();
                    produtoMontanteMap.put(pedido.getProduto(), pedido.getPreco());
                    return produtoMontanteMap;
                }).orElse(null);

        return new ResultadoValoresTotais(new Informacao(totalPedidosRealizados, totalProdutosVendidos, totalDeCategorias, montante, pedidoMaisBarato, pedidoMaisCaro));
    }
}
