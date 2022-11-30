package br.com.alura.clientelo.store.order;

import br.com.alura.clientelo.store.customer.Customer;
import br.com.alura.clientelo.store.customer.CustomerRepository;
import br.com.alura.clientelo.store.order.dto.CreateOrderRequest;
import br.com.alura.clientelo.store.order.dto.CreateOrderRequestConverter;
import br.com.alura.clientelo.store.order.dto.OrderItemsInformation;
import br.com.alura.clientelo.store.product.Product;
import br.com.alura.clientelo.store.product.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Order save(CreateOrderRequest request) {

        try {
            Order newOrder = new Order();
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

        return null;
    }

}
