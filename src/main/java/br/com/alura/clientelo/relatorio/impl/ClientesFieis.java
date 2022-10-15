package br.com.alura.clientelo.relatorio.impl;

import br.com.alura.clientelo.pedido.Pedido;
import br.com.alura.clientelo.relatorio.Relatorio;
import br.com.alura.clientelo.relatorio.resultado.impl.ResultadoClientesFieis;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class ClientesFieis implements Relatorio {

    @Override
    public ResultadoClientesFieis processar(List<Pedido> pedidos) {

        LinkedHashMap<String, Integer> resultado;

        Stream<String> clientes = pedidos.stream()
                .map(Pedido::getCliente)
                .distinct()
                .sorted();

        resultado = clientes.collect(toMap(cliente -> cliente, cliente -> pedidos.stream()
                .filter(pedido -> pedido.getCliente().equals(cliente))
                .toList().size(), (k, v) -> k, LinkedHashMap::new));

        return new ResultadoClientesFieis(resultado);
    }
}
