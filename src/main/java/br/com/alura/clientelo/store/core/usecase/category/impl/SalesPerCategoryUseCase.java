package br.com.alura.clientelo.store.core.usecase.category.impl;

import br.com.alura.clientelo.store.infra.repository.category.CategoryRepository;
import br.com.alura.clientelo.store.infra.repository.category.vo.SalesPerCategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesPerCategoryUseCase {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<SalesPerCategoryVO> execute() {
        return categoryRepository.getSalesPerCategory();
    }
}
