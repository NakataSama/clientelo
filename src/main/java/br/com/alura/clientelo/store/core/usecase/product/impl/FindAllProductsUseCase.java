package br.com.alura.clientelo.store.core.usecase.product.impl;

import br.com.alura.clientelo.store.core.entity.product.Product;
import br.com.alura.clientelo.store.infra.repository.product.ProductRepository;
import br.com.alura.clientelo.store.adapter.controller.product.dto.FindAllProductsResponse;
import br.com.alura.clientelo.store.adapter.controller.product.dto.FindAllProductsResponseConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllProductsUseCase {

    @Autowired
    private ProductRepository productRepository;

    public Page<FindAllProductsResponse> execute(Pageable pageable) {
        List<Product> products = productRepository.findAll();
        FindAllProductsResponseConverter converter = new FindAllProductsResponseConverter();
        List<FindAllProductsResponse> productsResponse = products.stream()
                .map(converter::from).toList();

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), products.size());

        return new PageImpl<>(productsResponse.subList(start, end), pageable, products.size());
    }
}
