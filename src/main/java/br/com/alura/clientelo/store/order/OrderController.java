package br.com.alura.clientelo.store.order;

import br.com.alura.clientelo.store.order.dto.CreateOrderRequest;
import br.com.alura.clientelo.store.product.Product;
import br.com.alura.clientelo.store.product.dto.GetAllProductsResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody @Valid CreateOrderRequest request) {
        try {
            Order order = service.save(request);
            return new ResponseEntity<>(order, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid order for creation.", e
            );
        }
    }

    @GetMapping
    public ResponseEntity<Page<GetAllProductsResponse>> getAll(Pageable pageable) {
        try {
            Page<GetAllProductsResponse> products = service.findAll(pageable);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, "No products found", e
            );
        }
    }
}
