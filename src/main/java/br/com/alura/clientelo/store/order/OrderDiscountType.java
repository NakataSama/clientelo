package br.com.alura.clientelo.store.order;

import java.math.BigDecimal;

public enum OrderDiscountType {
    LOYALTY(BigDecimal.valueOf(0.05)),
    NONE(BigDecimal.ONE);

    final BigDecimal value;

    OrderDiscountType(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }
}
