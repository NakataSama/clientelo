package br.com.alura.clientelo.store.core.usecase.order;

import br.com.alura.clientelo.store.core.entity.order.Order;
import br.com.alura.clientelo.store.core.usecase.order.impl.CreateOrderUseCase;
import br.com.alura.clientelo.store.core.usecase.order.impl.FindAllOrdersUseCase;
import br.com.alura.clientelo.store.core.usecase.order.impl.FindOrderByIdUseCase;
import br.com.alura.clientelo.store.order.dto.CreateOrderRequest;
import br.com.alura.clientelo.store.order.dto.FindAllOrdersResponse;
import br.com.alura.clientelo.store.order.dto.FindOrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private CreateOrderUseCase createOrderUseCase;
    @Autowired
    private FindOrderByIdUseCase findOrderByIdUseCase;
    @Autowired
    private FindAllOrdersUseCase findAllOrdersUseCase;

    public Order create(CreateOrderRequest request) {
        return createOrderUseCase.execute(request);
    }

    public FindOrderResponse findOrderDetailsById(Long id) {
        return findOrderByIdUseCase.execute(id);
    }

    public Page<FindAllOrdersResponse> findAll(Pageable pageable) {
        return findAllOrdersUseCase.execute(pageable);
    }

}
