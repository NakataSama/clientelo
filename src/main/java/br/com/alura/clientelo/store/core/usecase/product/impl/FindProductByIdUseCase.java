package br.com.alura.clientelo.store.core.usecase.product.impl;

import br.com.alura.clientelo.store.core.entity.product.Product;
import br.com.alura.clientelo.store.infra.repository.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindProductByIdUseCase {

    @Autowired
    private ProductRepository productRepository;

    public Product execute(Long id) {
        return productRepository.findById(id).orElseThrow();
    }
}
