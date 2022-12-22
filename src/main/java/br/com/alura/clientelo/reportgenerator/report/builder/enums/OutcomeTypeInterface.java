package br.com.alura.clientelo.reportgenerator.report.builder.enums;

public interface OutcomeTypeInterface<Result> {
    void apply(Result result);
}
