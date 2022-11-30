package br.com.alura.clientelo.store.category.dto;

import org.hibernate.validator.constraints.Length;

public class CreateCategoryRequest {

    @Length(min = 2)
    private final String name;

    public CreateCategoryRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
