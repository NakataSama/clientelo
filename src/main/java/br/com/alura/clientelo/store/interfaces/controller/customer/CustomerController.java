package br.com.alura.clientelo.store.interfaces.controller.customer;

import br.com.alura.clientelo.store.domain.customer.Customer;
import br.com.alura.clientelo.store.app.service.customer.CustomerService;
import br.com.alura.clientelo.store.interfaces.controller.customer.dto.CreateCustomerRequest;
import br.com.alura.clientelo.store.interfaces.controller.customer.dto.FindAllCustomersResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @PostMapping
    public ResponseEntity<Customer> create(@RequestBody @Valid CreateCustomerRequest request) {
        try {

            Customer customer = service.create(request);
            URI location = URI.create("/api/customers/%d".formatted(customer.getId()));
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.setLocation(location);

            return new ResponseEntity<>(customer, responseHeaders, HttpStatus.CREATED);

        } catch (RuntimeException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid product for creation.", e
            );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        try {
            Customer customer = service.findById(id);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, "No customer found.", e
            );
        }
    }

    @GetMapping
    public ResponseEntity<Page<FindAllCustomersResponse>> getAll(@PageableDefault(size = 5, sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
        try {
            Page<FindAllCustomersResponse> products = service.findAll(pageable);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, "No customers found", e
            );
        }
    }
}
