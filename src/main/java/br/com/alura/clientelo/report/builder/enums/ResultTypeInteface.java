package br.com.alura.clientelo.report.builder.enums;

public interface ResultTypeInteface<Result> {
    Object apply(Result result);
}
