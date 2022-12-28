package br.com.alura.clientelo.store.app.service.customer;

import br.com.alura.clientelo.store.interfaces.controller.customer.dto.CreateCustomerRequest;
import br.com.alura.clientelo.store.interfaces.controller.customer.dto.CreateCustomerRequestConverter;
import br.com.alura.clientelo.store.interfaces.controller.customer.dto.FindAllCustomersResponse;
import br.com.alura.clientelo.store.interfaces.controller.customer.dto.FindAllCustomersResponseConverter;
import br.com.alura.clientelo.store.domain.customer.Customer;
import br.com.alura.clientelo.store.infra.repository.customer.CustomerRepository;
import br.com.alura.clientelo.store.infra.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    public Customer create(CreateCustomerRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent())
            throw new RuntimeException("Email is already in use");

        CreateCustomerRequestConverter converter = new CreateCustomerRequestConverter();
        return customerRepository.save(converter.toCustomer(request));
    }

    public Customer findById(Long id) {
        return customerRepository.findById(id).orElseThrow();
    }

    public Page<FindAllCustomersResponse> findAll(Pageable pageable) {
        List<Customer> customers = customerRepository.findAll();
        FindAllCustomersResponseConverter converter = new FindAllCustomersResponseConverter();
        List<FindAllCustomersResponse> customersResponse = customers.stream()
                .map(converter::from).toList();

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), customers.size());

        return new PageImpl<>(customersResponse.subList(start, end), pageable, customers.size());
    }
}
