package br.com.alura.clientelo.relatorio.impl;

import br.com.alura.clientelo.Main;
import br.com.alura.clientelo.pedido.Pedido;
import br.com.alura.clientelo.relatorio.Relatorio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;

public class ClientesFieis implements Relatorio {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    private Integer numeroDePedidos = 0;

    @Override
    public void executa(List<Pedido> pedidos) {
        logger.info("##### RELATÓRIO DE CLIENTES FIÉIS ##### \n");
        List<String> clientes = pedidos.stream()
                                    .map(Pedido::getCliente)
                                    .distinct()
                                    .sorted()
                                    .toList();

        clientes.forEach(cliente -> {
            numeroDePedidos = pedidos.stream()
                    .filter(pedido -> pedido.getCliente().equals(cliente))
                    .toList().size();

            logger.info("Nº DE PEDIDOS: {}", numeroDePedidos);
            logger.info("NOME: {} \n", cliente);
        });
    }
}
