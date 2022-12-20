package br.com.alura.clientelo.store.product;

import br.com.alura.clientelo.store.category.Category;
import br.com.alura.clientelo.store.category.CategoryRepository;
import br.com.alura.clientelo.store.product.dto.CreateProductRequest;
import br.com.alura.clientelo.store.product.dto.CreateProductRequestConverter;
import br.com.alura.clientelo.store.product.dto.FindAllProductsResponse;
import br.com.alura.clientelo.store.product.dto.FindAllProductsResponseConverter;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public Product create(CreateProductRequest request) {
        Optional<Category> category = categoryRepository.findById(request.getCategoryId());
        CreateProductRequestConverter converter = new CreateProductRequestConverter();

        if(category.isEmpty()) {
            throw new RuntimeException("Category does not exists");
        }

        return productRepository.save(converter.toProduct(request));
    }

    public Page<FindAllProductsResponse> findAll(Pageable pageable) {
        List<Product> products = productRepository.findAll();
        FindAllProductsResponseConverter converter = new FindAllProductsResponseConverter();
        List<FindAllProductsResponse> productsResponse = products.stream()
                .map(converter::from).toList();

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), products.size());

        return new PageImpl<>(productsResponse.subList(start, end), pageable, products.size());
    }
}
