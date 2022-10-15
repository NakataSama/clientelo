package br.com.alura.clientelo.relatorio.impl;

import br.com.alura.clientelo.pedido.Pedido;
import br.com.alura.clientelo.relatorio.Relatorio;
import br.com.alura.clientelo.relatorio.resultado.impl.ResultadoVendasPorCategoria;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Stream;

public class VendasPorCategoria implements Relatorio {

    public static class Informacao {

        private final Integer quantidadeVendida;
        private final BigDecimal montante;

        public Informacao(Integer quantidadeVendida, BigDecimal montante) {
            this.quantidadeVendida = quantidadeVendida;
            this.montante = montante;
        }

        public Integer getQuantidadeVendida() {
            return quantidadeVendida;
        }

        public BigDecimal getMontante() {
            return montante;
        }
    }

    @Override
    public ResultadoVendasPorCategoria processar(List<Pedido> pedidos) {

        LinkedHashMap<String, Informacao> resultado = new LinkedHashMap<>();

        Stream<String> categorias = pedidos.stream()
                .map(Pedido::getCategoria)
                .distinct()
                .sorted();

        categorias.forEach(categoria -> {

            List<Pedido> pedidosPorCategoria = pedidos.stream()
                    .filter(pedido -> pedido.getCategoria().equals(categoria))
                    .toList();

            Integer quantidadeVendida = pedidosPorCategoria.size();

            BigDecimal montante = pedidosPorCategoria.stream()
                    .map(pedido -> pedido.getPreco().multiply(BigDecimal.valueOf(pedido.getQuantidade())))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            resultado.put(categoria, new Informacao(quantidadeVendida, montante));
        });

        return new ResultadoVendasPorCategoria(resultado);
    }
}
