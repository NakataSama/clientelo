package br.com.alura.clientelo.store.core.usecase.customer;

import br.com.alura.clientelo.store.core.entity.customer.Customer;
import br.com.alura.clientelo.store.core.usecase.customer.impl.CreateCustomerUseCase;
import br.com.alura.clientelo.store.core.usecase.customer.impl.FindAllCustomersUseCase;
import br.com.alura.clientelo.store.core.usecase.customer.impl.FindCustomerByIdUseCase;
import br.com.alura.clientelo.store.adapter.controller.customer.dto.CreateCustomerRequest;
import br.com.alura.clientelo.store.adapter.controller.customer.dto.FindAllCustomersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CreateCustomerUseCase createCustomerUseCase;
    @Autowired
    private FindCustomerByIdUseCase findCustomerByIdUseCase;
    @Autowired
    private FindAllCustomersUseCase findAllCustomersUseCase;

    public Customer create(CreateCustomerRequest request) {
        return createCustomerUseCase.execute(request);
    }

    public Customer findById(Long id) {
        return findCustomerByIdUseCase.execute(id);
    }

    public Page<FindAllCustomersResponse> findAll(Pageable pageable) {
        return findAllCustomersUseCase.execute(pageable);
    }
}
