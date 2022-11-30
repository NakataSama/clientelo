package br.com.alura.clientelo.store.product.dto;

import br.com.alura.clientelo.store.product.Product;

public class CreateProductRequestConverter {

    public Product convert(CreateProductRequest request) {
        return new Product(
                request.getName(),
                request.getPrice(),
                request.getDescription(),
                request.getItemsInStock(),
                request.getCategory()
        );
    }
}
