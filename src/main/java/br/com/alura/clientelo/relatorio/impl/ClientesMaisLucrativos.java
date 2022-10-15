package br.com.alura.clientelo.relatorio.impl;

import br.com.alura.clientelo.pedido.Pedido;
import br.com.alura.clientelo.relatorio.Relatorio;
import br.com.alura.clientelo.relatorio.resultado.impl.ResultadoClientesMaisLucrativos;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Stream;

public class ClientesMaisLucrativos implements Relatorio {

    Comparator<Pedido> comparadorDeMontante = (o1, o2) -> o2.getPreco().multiply(BigDecimal.valueOf(o2.getQuantidade()))
            .compareTo(o1.getPreco().multiply(BigDecimal.valueOf(o1.getQuantidade())));

    public static class Informacao {

        private final Integer numeroDePedidos;
        private final BigDecimal montante;

        public Informacao(Integer numeroDePedidos, BigDecimal montante) {
            this.numeroDePedidos = numeroDePedidos;
            this.montante = montante;
        }

        public Integer getNumeroDePedidos() {
            return numeroDePedidos;
        }

        public BigDecimal getMontante() {
            return montante;
        }
    }

    @Override
    public ResultadoClientesMaisLucrativos processar(List<Pedido> pedidos) {

        LinkedHashMap<String, Informacao> resultado = new LinkedHashMap<>();

        Stream<String> clientes = pedidos.stream()
                .sorted(comparadorDeMontante)
                .map(Pedido::getCliente)
                .distinct();

        clientes.forEach(cliente -> {

            List<Pedido> pedidosPorCliente = pedidos.stream()
                    .filter(pedido -> pedido.getCliente().equals(cliente))
                    .toList();

            Integer numeroDePedidos = pedidosPorCliente.size();

            BigDecimal montante = pedidosPorCliente.stream()
                    .map(pedido -> pedido.getPreco().multiply(BigDecimal.valueOf(pedido.getQuantidade())))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            resultado.put(cliente, new Informacao(numeroDePedidos, montante));
        });

        return new ResultadoClientesMaisLucrativos(resultado);
    }
}
