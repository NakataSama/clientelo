package br.com.alura.clientelo.store.interfaces.controller.order;

import br.com.alura.clientelo.store.interfaces.controller.order.dto.CreateOrderRequest;
import br.com.alura.clientelo.store.interfaces.controller.order.dto.FindAllOrdersResponse;
import br.com.alura.clientelo.store.interfaces.controller.order.dto.FindOrderResponse;
import br.com.alura.clientelo.store.domain.order.Order;
import br.com.alura.clientelo.store.app.service.order.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody @Valid CreateOrderRequest request) {
        try {
            Order order = service.create(request);
            return new ResponseEntity<>(order, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid order for creation.", e
            );
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("#email == authentication.principal.email")
    public ResponseEntity<FindOrderResponse> getOrderDetails(@PathVariable Long id, String email) {
        try {
            FindOrderResponse orderDetails = service.findOrderDetailsById(id);
            return new ResponseEntity<>(orderDetails, HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, "No order found.", e
            );
        }
    }

    @GetMapping
    public ResponseEntity<Page<FindAllOrdersResponse>> getAll(@PageableDefault(size = 5) Pageable pageable) {
        try {
            Page<FindAllOrdersResponse> orders = service.findAll(pageable);
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, "No Orders found", e
            );
        }
    }
}
