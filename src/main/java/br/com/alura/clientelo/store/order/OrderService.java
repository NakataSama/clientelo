package br.com.alura.clientelo.store.order;

import br.com.alura.clientelo.store.customer.Customer;
import br.com.alura.clientelo.store.customer.CustomerRepository;
import br.com.alura.clientelo.store.order.dto.*;
import br.com.alura.clientelo.store.product.Product;
import br.com.alura.clientelo.store.product.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Order create(CreateOrderRequest request) {

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

    public FindOrderResponse findOrderDetailsById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow();
        return new FindOrderResponseConverter().from(order);
    }

    public Page<FindAllOrdersResponse> findAll(Pageable pageable) {
        List<Order> orders = orderRepository.findByOrderByDateDescCustomerNameAsc();
        FindAllOrdersResponseConverter converter = new FindAllOrdersResponseConverter();
        List<FindAllOrdersResponse> ordersResponse = orders.stream()
                .map(converter::from).toList();

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), orders.size());

        return new PageImpl<>(ordersResponse.subList(start, end), pageable, orders.size());
    }

}
