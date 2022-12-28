package br.com.alura.clientelo.store.interfaces.controller.product.dto;

import br.com.alura.clientelo.store.domain.product.Product;

public class CreateProductRequestConverter {

    public Product toProduct(CreateProductRequest request) {
        return new Product(
                request.getName(),
                request.getPrice(),
                request.getDescription(),
                request.getItemsInStock(),
                request.getCategory()
        );
    }
}
