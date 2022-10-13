package br.com.alura.clientelo.relatorio.impl;

import br.com.alura.clientelo.Main;
import br.com.alura.clientelo.pedido.Pedido;
import br.com.alura.clientelo.relatorio.Relatorio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class VendasPorCategoria implements Relatorio {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private BigDecimal quantidadeVendida = BigDecimal.ZERO;
    private BigDecimal montante = BigDecimal.ZERO;

    @Override
    public void executa(List<Pedido> pedidos) {
        logger.info("##### RELATÓRIO DE VENDAS POR CATEGORIA ##### \n");
        List<String> categorias = pedidos.stream().map(Pedido::getCategoria).distinct().sorted().toList();

        categorias.forEach(categoria -> {
            List<Pedido> pedidosPorCategoria = pedidos.stream()
                    .filter(pedido -> pedido.getCategoria().equals(categoria))
                    .toList();

            quantidadeVendida = BigDecimal.valueOf(pedidosPorCategoria.size());
            montante = pedidosPorCategoria.stream()
                    .map(pedido -> pedido.getPreco().multiply(BigDecimal.valueOf(pedido.getQuantidade())))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            logger.info("CATEGORIA: {}", categoria);
            logger.info("QUANTIDADE VENDIDA: {}", quantidadeVendida);
            logger.info("QUANTIDADE MONTANTE: {} \n", montante);
        });
    }
}
