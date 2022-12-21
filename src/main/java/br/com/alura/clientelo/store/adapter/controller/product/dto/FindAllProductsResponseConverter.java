package br.com.alura.clientelo.store.adapter.controller.product.dto;

import br.com.alura.clientelo.store.core.entity.product.Product;

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
