package br.com.alura.clientelo.relatorio;

import br.com.alura.clientelo.pedido.Pedido;
import br.com.alura.clientelo.relatorio.resultado.Resultado;

import java.util.List;

public interface Relatorio {

    Resultado processar(List<Pedido> pedidos);
}
