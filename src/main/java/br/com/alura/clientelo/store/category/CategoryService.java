package br.com.alura.clientelo.store.category;

import br.com.alura.clientelo.store.category.dto.CreateCategoryRequest;
import br.com.alura.clientelo.store.category.dto.CreateCategoryRequestConverter;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public Category save(CreateCategoryRequest request) {
        CreateCategoryRequestConverter converter = new CreateCategoryRequestConverter();
        Optional<Category> category = categoryRepository.findByName(request.getName());

        if (category.isEmpty())
            return categoryRepository.save(converter.toCategory(request));

        throw new RuntimeException();
    }
}
