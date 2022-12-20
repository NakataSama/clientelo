package br.com.alura.clientelo.store.category;

import br.com.alura.clientelo.store.category.dto.CreateCategoryRequest;
import br.com.alura.clientelo.store.category.vo.SalesPerCategoryVO;
import br.com.alura.clientelo.store.core.entity.category.Category;
import br.com.alura.clientelo.store.core.usecase.category.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @PostMapping
    public ResponseEntity<Category> create(@RequestBody @Valid CreateCategoryRequest request) {
        try {

            Category category = service.save(request);
            URI location = URI.create("/api/categories/%d".formatted(category.getId()));
            HttpHeaders responseHeaders = new HttpHeaders();

            return new ResponseEntity<>(category, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Category name already exists.", e
            );
        }
    }

    @PatchMapping("/status/{id}")
    public ResponseEntity<Category> editStatus(@PathVariable Long id) {
        try {
            Category category = service.editStatus(id);

            URI location = URI.create("/api/categories/%d".formatted(category.getId()));
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.setLocation(location);

            return new ResponseEntity<>(category, responseHeaders, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid Product to edit", e
            );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        try {
            Category category = service.findById(id);
            return new ResponseEntity<>(category, HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, "No order found.", e
            );
        }
    }

    @GetMapping("/sales")
    public ResponseEntity<List<SalesPerCategoryVO>> getSalesPerCategory() {
        try {
            List<SalesPerCategoryVO> salesPerCategory = service.getSalesPerCategory();
            return new ResponseEntity<>(salesPerCategory, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, "No products found", e
            );
        }
    }
}
