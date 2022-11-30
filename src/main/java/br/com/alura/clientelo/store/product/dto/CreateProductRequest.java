package br.com.alura.clientelo.store.product.dto;

import br.com.alura.clientelo.store.category.Category;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class CreateProductRequest {

    @NotNull
    @Size(min = 2)
    private final String name;
    @DecimalMin("0.1")
    private final BigDecimal price;
    private final String description;
    @NotNull
    private final Integer itemsInStock;
    @NotNull
    private final Long categoryId;
    private Category category;

    public CreateProductRequest(String name, BigDecimal price, String description, Integer itemsInStock, Long categoryId) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.itemsInStock = itemsInStock;
        this.categoryId = categoryId;
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

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }
}
