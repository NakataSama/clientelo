package br.com.alura.clientelo.store.core.usecase.product.impl;

import br.com.alura.clientelo.store.infra.repository.category.CategoryRepository;
import br.com.alura.clientelo.store.core.entity.category.Category;
import br.com.alura.clientelo.store.core.entity.product.Product;
import br.com.alura.clientelo.store.infra.repository.product.ProductRepository;
import br.com.alura.clientelo.store.adapter.controller.product.dto.CreateProductRequest;
import br.com.alura.clientelo.store.adapter.controller.product.dto.CreateProductRequestConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CreateProductUseCase {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Product execute(CreateProductRequest request) {
        Optional<Category> category = categoryRepository.findById(request.getCategoryId());
        CreateProductRequestConverter converter = new CreateProductRequestConverter();

        if(category.isEmpty())
            throw new RuntimeException("Category does not exists");

        if(!category.get().isActive())
            throw new RuntimeException("Category is not active");

        return productRepository.save(converter.toProduct(request));
    }
}
