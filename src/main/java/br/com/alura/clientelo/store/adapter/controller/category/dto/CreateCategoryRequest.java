package br.com.alura.clientelo.store.adapter.controller.category.dto;

import jakarta.validation.constraints.Size;

public class CreateCategoryRequest {

    @Size(min = 2)
    private final String name;

    public CreateCategoryRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
