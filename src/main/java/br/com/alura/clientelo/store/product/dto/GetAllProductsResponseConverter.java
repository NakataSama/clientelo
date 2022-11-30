package br.com.alura.clientelo.store.product.dto;

import br.com.alura.clientelo.store.product.Product;

public class GetAllProductsResponseConverter {

    public GetAllProductsResponse from(Product product) {
        return new GetAllProductsResponse(
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getItemsInStock(),
                product.getCategory().getId(),
                product.getCategory().getName()
        );
    }
}
