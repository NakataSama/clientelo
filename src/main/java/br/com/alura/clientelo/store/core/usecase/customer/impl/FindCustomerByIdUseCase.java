package br.com.alura.clientelo.store.core.usecase.customer.impl;

import br.com.alura.clientelo.store.core.entity.customer.Customer;
import br.com.alura.clientelo.store.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindCustomerByIdUseCase {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer execute(Long id) {
        return customerRepository.findById(id).orElseThrow();
    }
}
