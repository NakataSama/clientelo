package br.com.alura.clientelo.relatorio.resultado;

import br.com.alura.clientelo.relatorio.Relatorio;

public interface Resultado<T extends Relatorio> {

    String gerarRelatorioEmTexto();
}