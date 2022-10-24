package br.com.alura.clientelo.report.result;

import br.com.alura.clientelo.report.Report;

public interface Result<T extends Report> {

    String generateText();
}