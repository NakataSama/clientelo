package br.com.alura.clientelo.store.infra.repository.customer.vo;

public class LoyalCustomersVO {

    private String name;
    private long numberOfOrders;

    public LoyalCustomersVO(String name, long numberOfOrders) {
        this.name = name;
        this.numberOfOrders = numberOfOrders;
    }

    public String getName() {
        return name;
    }

    public long getNumberOfOrders() {
        return numberOfOrders;
    }

    @Override
    public String toString() {
        return "LoyalCustomersVO{" +
                "name='" + name + '\'' +
                ", numberOfOrders=" + numberOfOrders +
                '}';
    }
}
