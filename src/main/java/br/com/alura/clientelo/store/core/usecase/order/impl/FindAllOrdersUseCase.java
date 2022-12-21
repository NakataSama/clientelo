package br.com.alura.clientelo.store.core.usecase.order.impl;

import br.com.alura.clientelo.store.core.entity.order.Order;
import br.com.alura.clientelo.store.infra.repository.order.OrderRepository;
import br.com.alura.clientelo.store.adapter.controller.order.dto.FindAllOrdersResponse;
import br.com.alura.clientelo.store.adapter.controller.order.dto.FindAllOrdersResponseConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllOrdersUseCase {

    @Autowired
    private OrderRepository orderRepository;

    public Page<FindAllOrdersResponse> execute(Pageable pageable) {
        List<Order> orders = orderRepository.findByOrderByDateDescCustomerNameAsc();
        FindAllOrdersResponseConverter converter = new FindAllOrdersResponseConverter();
        List<FindAllOrdersResponse> ordersResponse = orders.stream()
                .map(converter::from).toList();

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), orders.size());

        return new PageImpl<>(ordersResponse.subList(start, end), pageable, orders.size());
    }
}
