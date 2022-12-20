package br.com.alura.clientelo.store.order.dto;

public class OrderCustomer {
    private Long id;
    private String nome;

    public OrderCustomer(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
