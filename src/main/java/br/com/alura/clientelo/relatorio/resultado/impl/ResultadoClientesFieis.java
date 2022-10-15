package br.com.alura.clientelo.relatorio.resultado.impl;

import br.com.alura.clientelo.relatorio.impl.ClientesFieis;
import br.com.alura.clientelo.relatorio.resultado.Resultado;

import java.util.LinkedHashMap;

public class ResultadoClientesFieis implements Resultado<ClientesFieis> {

    private LinkedHashMap<String, Integer> resultado;

    public ResultadoClientesFieis(LinkedHashMap<String, Integer> resultado) {
        this.resultado = resultado;
    }

    @Override
    public String gerarRelatorioEmTexto() {

        StringBuilder resposta = new StringBuilder();
        resposta.append("##### RELATÓRIO DE CLIENTES FIÉIS ##### \n");
        resposta.append("\n");

        resultado.forEach((cliente, numeroDePedidos) -> {
            resposta.append(String.format("NOME: %s \n", cliente));
            resposta.append(String.format("Nº DE PEDIDOS: %s \n", numeroDePedidos));
            resposta.append("\n");
        });

        resposta.append("##### FIM DO RELATÓRIO ##### \n");
        return resposta.toString();
    }
}
