package br.com.alura.clientelo.store.core.usecase.category.impl;

import br.com.alura.clientelo.store.category.CategoryRepository;
import br.com.alura.clientelo.store.core.entity.category.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EditCategoryStatusUseCase {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private FindCategoryByIdUseCase findCategoryByIdUseCase;

    @Transactional
    public Category execute(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow().changeStatus();
        return categoryRepository.save(category);
    }
}
