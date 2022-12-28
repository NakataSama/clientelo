package br.com.alura.clientelo.store.app.service.category;

import br.com.alura.clientelo.store.interfaces.controller.category.dto.CreateCategoryRequest;
import br.com.alura.clientelo.store.interfaces.controller.category.dto.CreateCategoryRequestConverter;
import br.com.alura.clientelo.store.domain.category.Category;
import br.com.alura.clientelo.store.infra.repository.category.CategoryRepository;
import br.com.alura.clientelo.store.infra.repository.category.vo.SalesPerCategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category save(CreateCategoryRequest request) {
        CreateCategoryRequestConverter converter = new CreateCategoryRequestConverter();
        Optional<Category> category = categoryRepository.findByName(request.getName());

        if (category.isEmpty())
            return categoryRepository.save(converter.toCategory(request));

        throw new RuntimeException();
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow();
    }

    public List<SalesPerCategoryVO> getSalesPerCategory() {
        return categoryRepository.getSalesPerCategory();
    }

    public Category editStatus(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow().changeStatus();
        return categoryRepository.save(category);
    }
}
