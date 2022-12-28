package br.com.alura.clientelo.store.interfaces.controller.category.dto;

import br.com.alura.clientelo.store.domain.category.Category;

public class CreateCategoryRequestConverter {
    public Category toCategory(CreateCategoryRequest request) {
        return new Category(request.getName());
    }
}
