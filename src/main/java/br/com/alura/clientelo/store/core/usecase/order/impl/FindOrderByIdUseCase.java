package br.com.alura.clientelo.store.core.usecase.order.impl;

import br.com.alura.clientelo.store.core.entity.order.Order;
import br.com.alura.clientelo.store.order.OrderRepository;
import br.com.alura.clientelo.store.order.dto.FindOrderResponse;
import br.com.alura.clientelo.store.order.dto.FindOrderResponseConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindOrderByIdUseCase {

    @Autowired
    private OrderRepository orderRepository;

    public FindOrderResponse execute(Long id) {
        Order order = orderRepository.findById(id).orElseThrow();
        return new FindOrderResponseConverter().from(order);
    }
}
