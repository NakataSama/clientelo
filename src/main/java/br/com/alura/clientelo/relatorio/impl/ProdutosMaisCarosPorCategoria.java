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

public class ProdutosMaisCarosPorCategoria implements Relatorio {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    @Override
    public void executa(List<Pedido> pedidos) {
        logger.info("##### RELATÓRIO DE PRODUTOS MAIS CAROS POR CATEGORIA ##### \n");
        List<String> categorias = pedidos.stream().map(Pedido::getCategoria).distinct().sorted().toList();

        categorias.forEach(categoria -> {

            List<Pedido> pedidosFiltrados = pedidos.stream().filter(pedido -> pedido.getCategoria().equals(categoria))
                    .sorted(Comparator.comparing(Pedido::getPreco).reversed()).toList();

            BigDecimal preco = pedidosFiltrados.stream()
                                .map(Pedido::getPreco).findFirst().get();

            String produto = pedidosFiltrados.stream()
                                .map(Pedido::getProduto).findFirst().get();

            logger.info("CATEGORIA: {}", categoria);
            logger.info("PRODUTO: {}", produto);
            logger.info("PREÇO: {} \n", preco);
        });
    }
}
