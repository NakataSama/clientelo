package br.com.alura.clientelo.relatorio.impl;

import br.com.alura.clientelo.Main;
import br.com.alura.clientelo.pedido.Pedido;
import br.com.alura.clientelo.relatorio.Relatorio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProdutosMaisVendidos implements Relatorio {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    @Override
    public void executa(List<Pedido> pedidos) {
        logger.info("##### RELATÓRIO DE PRODUTOS MAIS VENDIDOS ##### \n");
        pedidos.stream().sorted(Comparator.comparing(Pedido::getQuantidade).reversed()).limit(3)
                .forEach(pedido -> {
                    logger.info("CATEGORIA: {}", pedido.getCategoria());
                    logger.info("PRODUTO: {}", pedido.getProduto());
                    logger.info("QUANTIDADE VENDIDA: {} \n", pedido.getQuantidade());
                });
    }
}
