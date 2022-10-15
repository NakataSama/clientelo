package br.com.alura.clientelo.relatorio.resultado.impl;

import br.com.alura.clientelo.relatorio.impl.ProdutosMaisCarosPorCategoria;
import br.com.alura.clientelo.relatorio.resultado.Resultado;
import br.com.alura.clientelo.util.FormatadorDeMoeda;

import java.util.LinkedHashMap;

public class ResultadoProdutosMaisCarosPorCategoria implements Resultado<ProdutosMaisCarosPorCategoria> {

    LinkedHashMap<String, ProdutosMaisCarosPorCategoria.Informacao> resultado;
    public ResultadoProdutosMaisCarosPorCategoria(LinkedHashMap<String, ProdutosMaisCarosPorCategoria.Informacao> resultado) {
        this.resultado = resultado;
    }

    @Override
    public String gerarRelatorioEmTexto() {

        StringBuilder resposta = new StringBuilder();
        resposta.append("##### RELATÓRIO DE PRODUTOS MAIS CAROS POR CATEGORIA ##### \n");
        resposta.append("\n");

        resultado.forEach((categoria, informacao) -> {

            resposta.append(String.format("CATEGORIA: %s \n", categoria));
            resposta.append(String.format("PRODUTO: %s \n", informacao.getProduto()));
            resposta.append(String.format("PREÇO: %s \n", FormatadorDeMoeda.CONVERTE_PARA_REAL(informacao.getPreco())));
            resposta.append("\n");
        });

        resposta.append("##### FIM DO RELATÓRIO ##### \n");
        return resposta.toString();
    }
}
