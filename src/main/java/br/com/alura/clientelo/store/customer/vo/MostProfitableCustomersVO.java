package br.com.alura.clientelo.store.customer.vo;

import java.math.BigDecimal;

public class MostProfitableCustomersVO {

    private String name;
    private long numberOfOrders;
    private BigDecimal totalAmount;

    public MostProfitableCustomersVO(String name, long numberOfOrders, BigDecimal totalAmount) {
        this.name = name;
        this.numberOfOrders = numberOfOrders;
        this.totalAmount = totalAmount;
    }

    public String getName() {
        return name;
    }

    public long getNumberOfOrders() {
        return numberOfOrders;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    @Override
    public String toString() {
        return "MostProfitableCustomersVO{" +
                "name='" + name + '\'' +
                ", numberOfOrders=" + numberOfOrders +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
