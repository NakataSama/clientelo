package br.com.alura.clientelo.store.product.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

public class EditProductRequest {

    @Length(min = 2)
    private String name;
    @DecimalMin("0.1")
    private BigDecimal price;
    @Nullable
    private String description;

    @Min(1)
    private int stock;

    public EditProductRequest(String name, BigDecimal price, String description, int stock) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.stock = stock;
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

    public int getStock() {
        return stock;
    }
}
