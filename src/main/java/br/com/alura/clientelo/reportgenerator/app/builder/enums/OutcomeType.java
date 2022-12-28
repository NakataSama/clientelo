package br.com.alura.clientelo.reportgenerator.app.builder.enums;


import br.com.alura.clientelo.reportgenerator.domain.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum OutcomeType implements OutcomeTypeInterface<Result> {
    CONSOLE {
        @Override
        public void apply(Result result) {
            System.out.println(result.generateText());
        }
    },
    LOG {
        @Override
        public void apply(Result result) {
            final Logger logger = LoggerFactory.getLogger(OutcomeType.class);
            logger.info(result.generateText());
        }
    }
}
