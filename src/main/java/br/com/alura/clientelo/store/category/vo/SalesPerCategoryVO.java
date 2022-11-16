package br.com.alura.clientelo.store.category.vo;

import java.math.BigDecimal;

public class SalesPerCategoryVO {
    private String name;
    private long quantity;
    private BigDecimal totalAmount;

    public SalesPerCategoryVO(String name, long quantity, BigDecimal totalAmount) {
        this.name = name;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
    }

    public String getName() {
        return name;
    }

    public long getQuantity() {
        return quantity;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    @Override
    public String toString() {
        return "SalesPerCategoryVO{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
