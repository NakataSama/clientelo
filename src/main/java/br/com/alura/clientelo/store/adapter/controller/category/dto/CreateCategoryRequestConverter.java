package br.com.alura.clientelo.store.adapter.controller.category.dto;

import br.com.alura.clientelo.store.core.entity.category.Category;

public class CreateCategoryRequestConverter {
    public Category toCategory(CreateCategoryRequest request) {
        return new Category(request.getName());
    }
}
