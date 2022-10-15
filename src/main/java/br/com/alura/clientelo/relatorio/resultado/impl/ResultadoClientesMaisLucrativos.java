package br.com.alura.clientelo.relatorio.resultado.impl;

import br.com.alura.clientelo.relatorio.impl.ClientesMaisLucrativos;
import br.com.alura.clientelo.relatorio.resultado.Resultado;
import br.com.alura.clientelo.util.FormatadorDeMoeda;

import java.util.LinkedHashMap;

public class ResultadoClientesMaisLucrativos implements Resultado<ClientesMaisLucrativos> {

    LinkedHashMap<String, ClientesMaisLucrativos.Informacao> resultado;

    public ResultadoClientesMaisLucrativos(LinkedHashMap<String, ClientesMaisLucrativos.Informacao> resultado) {
        this.resultado = resultado;
    }

    @Override
    public String gerarRelatorioEmTexto() {

        StringBuilder resposta = new StringBuilder();
        resposta.append("##### RELATÓRIO DE CLIENTES MAIS LUCRATIVOS ##### \n");
        resposta.append("\n");

        resultado.forEach((cliente, informacao) -> {
            resposta.append(String.format("NOME: %s \n", cliente));
            resposta.append(String.format("Nº DE PEDIDOS: %s \n", informacao.getNumeroDePedidos()));
            resposta.append(String.format("MONTANTE GASTO: %s \n", FormatadorDeMoeda.CONVERTE_PARA_REAL(informacao.getMontante())));
            resposta.append("\n");
        });

        resposta.append("##### FIM DO RELATÓRIO ##### \n");
        return resposta.toString();
    }
}
