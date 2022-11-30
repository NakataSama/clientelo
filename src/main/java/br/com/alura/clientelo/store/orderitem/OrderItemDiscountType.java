package br.com.alura.clientelo.store.orderitem;

import java.math.BigDecimal;

public enum OrderItemDiscountType {
    QUANTITY(BigDecimal.valueOf(0.10)),
    SALE(BigDecimal.valueOf(0.20)),
    NONE(BigDecimal.ONE);

    final BigDecimal value;

    OrderItemDiscountType(BigDecimal value) {
        this.value = value;
    }
    public BigDecimal getValue() {
        return value;
    }
}
