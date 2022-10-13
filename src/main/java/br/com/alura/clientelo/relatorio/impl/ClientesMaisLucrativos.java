package br.com.alura.clientelo.relatorio.impl;

import br.com.alura.clientelo.Main;
import br.com.alura.clientelo.pedido.Pedido;
import br.com.alura.clientelo.relatorio.Relatorio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;

public class ClientesMaisLucrativos implements Relatorio {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    private BigDecimal montante = BigDecimal.ZERO;

    private Integer numeroDePedidos = 0;
    @Override
    public void executa(List<Pedido> pedidos) {
        logger.info("##### RELATÓRIO DE CLIENTES MAIS LUCRATIVOS ##### \n");
        List<String> clientes = pedidos.stream()
                                    .map(Pedido::getCliente)
                                    .distinct()
                                    .sorted()
                                    .toList();

        clientes.forEach(cliente -> {
            List<Pedido> pedidosPorCliente = pedidos.stream()
                    .filter(pedido -> pedido.getCliente().equals(cliente))
                    .toList();

            numeroDePedidos = pedidosPorCliente.size();

            montante = pedidosPorCliente.stream()
                    .map(pedido -> pedido.getPreco().multiply(BigDecimal.valueOf(pedido.getQuantidade())))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            logger.info("NOME: {}", cliente);
            logger.info("Nº DE PEDIDOS: {}", numeroDePedidos);
            logger.info("MONTANTE GASTO: {} \n", montante);
        });
    }
}
