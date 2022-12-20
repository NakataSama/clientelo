package br.com.alura.clientelo.store.product.dto;

import br.com.alura.clientelo.store.core.entity.product.Product;

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
