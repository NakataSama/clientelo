package br.com.alura.clientelo.report.builder.enums;

public interface OutcomeTypeInterface<Result> {
    void apply(Result result);
}
