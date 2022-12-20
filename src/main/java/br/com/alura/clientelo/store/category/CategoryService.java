package br.com.alura.clientelo.store.category;

import br.com.alura.clientelo.store.category.dto.CreateCategoryRequest;
import br.com.alura.clientelo.store.category.dto.CreateCategoryRequestConverter;
import br.com.alura.clientelo.store.category.vo.SalesPerCategoryVO;
import br.com.alura.clientelo.store.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;

    @Transactional
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

    @Transactional
    public Category editStatus(Long id) {
        Category category = findById(id).changeStatus();
        return categoryRepository.save(category);
    }
}
