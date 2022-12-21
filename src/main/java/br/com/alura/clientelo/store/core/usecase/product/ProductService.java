package br.com.alura.clientelo.store.core.usecase.product;

import br.com.alura.clientelo.store.core.entity.product.Product;
import br.com.alura.clientelo.store.core.usecase.product.impl.CreateProductUseCase;
import br.com.alura.clientelo.store.core.usecase.product.impl.EditProductUseCase;
import br.com.alura.clientelo.store.core.usecase.product.impl.FindAllProductsUseCase;
import br.com.alura.clientelo.store.core.usecase.product.impl.FindProductByIdUseCase;
import br.com.alura.clientelo.store.adapter.controller.product.dto.CreateProductRequest;
import br.com.alura.clientelo.store.adapter.controller.product.dto.EditProductRequest;
import br.com.alura.clientelo.store.adapter.controller.product.dto.FindAllProductsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private CreateProductUseCase createProductUseCase;
    @Autowired
    private FindProductByIdUseCase findProductByIdUseCase;
    @Autowired
    private EditProductUseCase editProductUseCase;
    @Autowired
    private FindAllProductsUseCase findAllProductsUseCase;

    public Product create(CreateProductRequest request) {
        return createProductUseCase.execute(request);
    }

    public Product findById(Long id) {
        return findProductByIdUseCase.execute(id);
    }

    public Product editProduct(Long id, EditProductRequest request) {
        return editProductUseCase.execute(id, request);
    }

    public Page<FindAllProductsResponse> findAll(Pageable pageable) {
        return findAllProductsUseCase.execute(pageable);
    }
}
