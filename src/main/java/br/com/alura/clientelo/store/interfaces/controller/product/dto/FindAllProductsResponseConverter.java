package br.com.alura.clientelo.store.interfaces.controller.product.dto;

import br.com.alura.clientelo.store.domain.product.Product;

public class FindAllProductsResponseConverter {

    public FindAllProductsResponse from(Product product) {
        return new FindAllProductsResponse(
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getItemsInStock(),
                product.getCategory().getId(),
                product.getCategory().getName()
        );
    }
}
