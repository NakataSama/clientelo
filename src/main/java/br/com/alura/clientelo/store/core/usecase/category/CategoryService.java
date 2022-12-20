package br.com.alura.clientelo.store.core.usecase.category;

import br.com.alura.clientelo.store.category.dto.CreateCategoryRequest;
import br.com.alura.clientelo.store.category.vo.SalesPerCategoryVO;
import br.com.alura.clientelo.store.core.entity.category.Category;
import br.com.alura.clientelo.store.core.usecase.category.impl.CreateCategoryUseCase;
import br.com.alura.clientelo.store.core.usecase.category.impl.EditCategoryStatusUseCase;
import br.com.alura.clientelo.store.core.usecase.category.impl.FindCategoryByIdUseCase;
import br.com.alura.clientelo.store.core.usecase.category.impl.SalesPerCategoryUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CreateCategoryUseCase createCategoryUseCase;

    @Autowired
    FindCategoryByIdUseCase findCategoryByIdUseCase;

    @Autowired
    SalesPerCategoryUseCase salesPerCategoryUseCase;

    @Autowired
    EditCategoryStatusUseCase editCategoryStatusUseCase;

    public Category save(CreateCategoryRequest request) {
        return createCategoryUseCase.execute(request);
    }

    public Category findById(Long id) {
        return findCategoryByIdUseCase.execute(id);
    }

    public List<SalesPerCategoryVO> getSalesPerCategory() {
        return salesPerCategoryUseCase.execute();
    }

    public Category editStatus(Long id) {
        return editCategoryStatusUseCase.execute(id);
    }
}
