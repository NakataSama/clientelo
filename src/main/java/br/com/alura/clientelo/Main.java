package br.com.alura.clientelo;

import br.com.alura.clientelo.pedido.Pedido;
import br.com.alura.clientelo.processador.ProcessadorDeCsv;
import br.com.alura.clientelo.relatorio.impl.*;
import br.com.alura.clientelo.relatorio.resultado.impl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        List<Pedido> pedidos = ProcessadorDeCsv.processaArquivo("pedidos.csv");

        ValoresTotais valoresTotais = new ValoresTotais();
        VendasPorCategoria vendasPorCategoria = new VendasPorCategoria();
        ProdutosMaisVendidos produtosMaisVendidos = new ProdutosMaisVendidos();
        ProdutosMaisCarosPorCategoria produtosMaisCarosPorCategoria = new ProdutosMaisCarosPorCategoria();
        ClientesFieis clientesFieis = new ClientesFieis();
        ClientesMaisLucrativos clientesMaisLucrativos = new ClientesMaisLucrativos();

        ResultadoValoresTotais resultadoValoresTotais = valoresTotais.processar(pedidos);
        System.out.println(resultadoValoresTotais.gerarRelatorioEmTexto());

        ResultadoVendasPorCategoria resultadoVendasPorCategoria = vendasPorCategoria.processar(pedidos);
        System.out.println(resultadoVendasPorCategoria.gerarRelatorioEmTexto());

        ResultadoProdutosMaisVendidos resultadoProdutosMaisVendidos = produtosMaisVendidos.processar(pedidos);
        System.out.println(resultadoProdutosMaisVendidos.gerarRelatorioEmTexto());

        ResultadoProdutosMaisCarosPorCategoria resultadoProdutosMaisCarosPorCategoria = produtosMaisCarosPorCategoria.processar(pedidos);
        System.out.println(resultadoProdutosMaisCarosPorCategoria.gerarRelatorioEmTexto());

        ResultadoClientesFieis resultadoClientesFieis = clientesFieis.processar(pedidos);
        System.out.println(resultadoClientesFieis.gerarRelatorioEmTexto());

        ResultadoClientesMaisLucrativos resultadoClientesMaisLucrativos = clientesMaisLucrativos.processar(pedidos);
        System.out.println(resultadoClientesMaisLucrativos.gerarRelatorioEmTexto());
    }
}

