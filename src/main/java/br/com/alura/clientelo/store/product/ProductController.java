package br.com.alura.clientelo.store.product;

import br.com.alura.clientelo.store.core.entity.product.Product;
import br.com.alura.clientelo.store.core.usecase.product.ProductService;
import br.com.alura.clientelo.store.product.dto.CreateProductRequest;
import br.com.alura.clientelo.store.product.dto.EditProductRequest;
import br.com.alura.clientelo.store.product.dto.FindAllProductsResponse;
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
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody @Valid CreateProductRequest request) {
        try {

            Product product = service.create(request);
            URI location = URI.create("/api/products/%s".formatted(product.getId()));
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.setLocation(location);

            return new ResponseEntity<>(product, responseHeaders, HttpStatus.CREATED);

        } catch (RuntimeException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid product for creation.", e
            );
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Product> editProduct(@PathVariable Long id, @RequestBody @Valid EditProductRequest request) {
        try {
            Product product = service.editProduct(id, request);

            URI location = URI.create("/api/products/%d".formatted(product.getId()));
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.setLocation(location);

            return new ResponseEntity<>(product, responseHeaders, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid Product to edit", e
            );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        try {
            Product product = service.findById(id);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, "No product found.", e
            );
        }
    }

    @GetMapping
    public ResponseEntity<Page<FindAllProductsResponse>> getAll(@PageableDefault(size = 5, sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
        try {
            Page<FindAllProductsResponse> products = service.findAll(pageable);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, "No products found", e
            );
        }
    }
}
