package br.com.alura.clientelo.store.interfaces.controller.product.dto;

import java.math.BigDecimal;

public class FindAllProductsResponse {

    private final String name;
    private final BigDecimal price;
    private final String description;
    private final Integer itemsInStock;
    private final Long categoryId;
    private final String categoryName;

    public FindAllProductsResponse(String name, BigDecimal price, String description, Integer itemsInStock, Long categoryId, String categoryName) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.itemsInStock = itemsInStock;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public Integer getItemsInStock() {
        return itemsInStock;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
