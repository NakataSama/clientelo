package br.com.alura.clientelo.store.product;

import br.com.alura.clientelo.store.product.dto.CreateProductRequest;
import br.com.alura.clientelo.store.product.dto.GetAllProductsResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody @Valid CreateProductRequest request) {
        try {
            Product product = service.save(request);
            return new ResponseEntity<>(product, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Category does not exists, product will not be created.", e
            );
        }
    }

    @GetMapping
    public ResponseEntity<Page<GetAllProductsResponse>> getAll(@PageableDefault(size = 5, sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
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
