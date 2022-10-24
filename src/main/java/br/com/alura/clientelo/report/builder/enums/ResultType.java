package br.com.alura.clientelo.report.builder.enums;


import br.com.alura.clientelo.report.result.Result;

public enum ResultType implements ResultTypeInteface<Result> {
    TEXT {
        @Override
        public String apply(Result result) {
            return result.generateText();
        }
    }
}
