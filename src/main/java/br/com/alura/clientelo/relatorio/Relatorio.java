package br.com.alura.clientelo.relatorio;

import br.com.alura.clientelo.pedido.Pedido;

import java.util.List;

public interface Relatorio {

    void executa(List<Pedido> pedidos);
}
