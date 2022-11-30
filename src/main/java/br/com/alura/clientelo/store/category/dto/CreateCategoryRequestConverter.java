package br.com.alura.clientelo.store.category.dto;

import br.com.alura.clientelo.store.category.Category;

public class CreateCategoryRequestConverter {
    public Category toCategory(CreateCategoryRequest request) {
        return new Category(request.getName());
    }
}
