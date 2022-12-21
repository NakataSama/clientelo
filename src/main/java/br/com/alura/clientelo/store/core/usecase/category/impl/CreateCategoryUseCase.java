package br.com.alura.clientelo.store.core.usecase.category.impl;

import br.com.alura.clientelo.store.infra.repository.category.CategoryRepository;
import br.com.alura.clientelo.store.adapter.controller.category.dto.CreateCategoryRequest;
import br.com.alura.clientelo.store.adapter.controller.category.dto.CreateCategoryRequestConverter;
import br.com.alura.clientelo.store.core.entity.category.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CreateCategoryUseCase {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public Category execute(CreateCategoryRequest request) {
        CreateCategoryRequestConverter converter = new CreateCategoryRequestConverter();
        Optional<Category> category = categoryRepository.findByName(request.getName());

        if (category.isEmpty())
            return categoryRepository.save(converter.toCategory(request));

        throw new RuntimeException();
    }
}
