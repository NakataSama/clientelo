package br.com.alura.clientelo.relatorio.resultado.impl;

import br.com.alura.clientelo.relatorio.impl.VendasPorCategoria;
import br.com.alura.clientelo.relatorio.resultado.Resultado;
import br.com.alura.clientelo.util.FormatadorDeMoeda;

import java.util.LinkedHashMap;

public class ResultadoVendasPorCategoria implements Resultado<VendasPorCategoria> {

    LinkedHashMap<String, VendasPorCategoria.Informacao> resultado;

    public ResultadoVendasPorCategoria(LinkedHashMap<String, VendasPorCategoria.Informacao> resultado) {
        this.resultado = resultado;
    }

    @Override
    public String gerarRelatorioEmTexto() {

        StringBuilder resposta = new StringBuilder();
        resposta.append("##### RELATÓRIO DE VENDAS POR CATEGORIA ##### \n");
        resposta.append("\n");

        resultado.forEach((categoria, informacao) -> {

            resposta.append(String.format("CATEGORIA: %s \n", categoria));
            resposta.append(String.format("QUANTIDADE VENDIDA: %s \n", informacao.getQuantidadeVendida()));
            resposta.append(String.format("QUANTIDADE MONTANTE: %s \n", FormatadorDeMoeda.CONVERTE_PARA_REAL(informacao.getMontante())));
            resposta.append("\n");
        });

        resposta.append("##### FIM DO RELATÓRIO ##### \n");
        return resposta.toString();
    }
}
