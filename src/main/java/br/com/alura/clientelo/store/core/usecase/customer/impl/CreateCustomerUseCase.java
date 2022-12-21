package br.com.alura.clientelo.store.core.usecase.customer.impl;

import br.com.alura.clientelo.store.core.entity.customer.Customer;
import br.com.alura.clientelo.store.infra.repository.customer.CustomerRepository;
import br.com.alura.clientelo.store.adapter.controller.customer.dto.CreateCustomerRequest;
import br.com.alura.clientelo.store.adapter.controller.customer.dto.CreateCustomerRequestConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateCustomerUseCase {

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    public Customer execute(CreateCustomerRequest request) {
        CreateCustomerRequestConverter converter = new CreateCustomerRequestConverter();
        return customerRepository.save(converter.toCustomer(request));
    }
}
