package br.com.alura.clientelo.store.app.service.product;

import br.com.alura.clientelo.store.domain.category.Category;
import br.com.alura.clientelo.store.domain.product.Product;
import br.com.alura.clientelo.store.infra.repository.category.CategoryRepository;
import br.com.alura.clientelo.store.infra.repository.product.ProductRepository;
import br.com.alura.clientelo.store.interfaces.controller.product.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        if(category.isEmpty())
            throw new RuntimeException("Category does not exists");

        if(!category.get().isActive())
            throw new RuntimeException("Category is not active");

        return productRepository.save(converter.toProduct(request));
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    @Transactional
    public Product editProduct(Long id, EditProductRequest request) {
        Product product = productRepository.findById(id).orElseThrow();

        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription() != null ? request.getDescription() : null);
        product.setItemsInStock(request.getStock());

        return productRepository.save(product);
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
