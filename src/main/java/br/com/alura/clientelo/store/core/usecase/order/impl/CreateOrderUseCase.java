package br.com.alura.clientelo.store.core.usecase.order.impl;

import br.com.alura.clientelo.store.core.entity.customer.Customer;
import br.com.alura.clientelo.store.core.entity.order.Order;
import br.com.alura.clientelo.store.core.entity.order.OrderDiscountType;
import br.com.alura.clientelo.store.core.entity.product.Product;
import br.com.alura.clientelo.store.customer.CustomerRepository;
import br.com.alura.clientelo.store.order.OrderRepository;
import br.com.alura.clientelo.store.order.dto.CreateOrderRequest;
import br.com.alura.clientelo.store.order.dto.CreateOrderRequestConverter;
import br.com.alura.clientelo.store.order.dto.OrderItemsInformation;
import br.com.alura.clientelo.store.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CreateOrderUseCase {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public Order execute(CreateOrderRequest request) {

        try {
            CreateOrderRequestConverter converter = new CreateOrderRequestConverter();
            Optional<Customer> customer = customerRepository.findById(request.getCustomerId());

            if (customer.isEmpty())
                throw new RuntimeException("Invalid customer.");

            Set<Long> productIds = request.getInformation().stream()
                    .map(OrderItemsInformation::getProductId)
                    .collect(Collectors.toSet());

            List<Product> products = productIds.stream()
                    .map(productId -> productRepository.findById(productId).orElseThrow())
                    .toList();

            if (products.isEmpty())
                throw new RuntimeException("No valid products");

            List<Order> customerOrders = orderRepository.findByCustomerId(customer.get().getId());

            return converter.toOrder(
                    request.getInformation(),
                    customerOrders.size() > 5 ? OrderDiscountType.LOYALTY : OrderDiscountType.NONE,
                    customer.get(),
                    products
            );

        } catch (Exception e) {
            e.printStackTrace();
        }

        throw new RuntimeException();
    }
}
