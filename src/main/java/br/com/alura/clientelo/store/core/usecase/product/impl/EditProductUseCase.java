package br.com.alura.clientelo.store.core.usecase.product.impl;

import br.com.alura.clientelo.store.core.entity.product.Product;
import br.com.alura.clientelo.store.infra.repository.product.ProductRepository;
import br.com.alura.clientelo.store.adapter.controller.product.dto.EditProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EditProductUseCase {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Product execute(Long id, EditProductRequest request) {
        Product product = productRepository.findById(id).orElseThrow();

        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription() != null ? request.getDescription() : null);
        product.setItemsInStock(request.getStock());

        return productRepository.save(product);
    }
}
