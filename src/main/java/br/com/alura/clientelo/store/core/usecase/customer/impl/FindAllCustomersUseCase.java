package br.com.alura.clientelo.store.core.usecase.customer.impl;

import br.com.alura.clientelo.store.core.entity.customer.Customer;
import br.com.alura.clientelo.store.customer.CustomerRepository;
import br.com.alura.clientelo.store.customer.dto.FindAllCustomersResponse;
import br.com.alura.clientelo.store.customer.dto.FindAllCustomersResponseConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllCustomersUseCase {

    @Autowired
    private CustomerRepository customerRepository;

    public Page<FindAllCustomersResponse> execute(Pageable pageable) {
        List<Customer> customers = customerRepository.findAll();
        FindAllCustomersResponseConverter converter = new FindAllCustomersResponseConverter();
        List<FindAllCustomersResponse> customersResponse = customers.stream()
                .map(converter::from).toList();

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), customers.size());

        return new PageImpl<>(customersResponse.subList(start, end), pageable, customers.size());
    }
}
