package br.com.alura.clientelo.store.category.vo;

import java.math.BigDecimal;

public class SalesPerCategoryVO {
    private String name;
    private long quantity;
    private BigDecimal totalAmount;

    public SalesPerCategoryVO() {
    }

    public SalesPerCategoryVO(String name, long quantity, BigDecimal totalAmount) {
        this.name = name;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
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
