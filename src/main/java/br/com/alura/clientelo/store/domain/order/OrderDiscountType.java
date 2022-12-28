package br.com.alura.clientelo.store.domain.order;

import java.math.BigDecimal;

public enum OrderDiscountType {
    LOYALTY(BigDecimal.valueOf(0.05)),
    NONE(BigDecimal.ZERO);

    final BigDecimal value;

    OrderDiscountType(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }
}
