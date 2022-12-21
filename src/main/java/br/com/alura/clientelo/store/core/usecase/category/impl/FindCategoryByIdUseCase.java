package br.com.alura.clientelo.store.core.usecase.category.impl;

import br.com.alura.clientelo.store.infra.repository.category.CategoryRepository;
import br.com.alura.clientelo.store.core.entity.category.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindCategoryByIdUseCase {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category execute(Long id) {
        return categoryRepository.findById(id).orElseThrow();
    }
}
