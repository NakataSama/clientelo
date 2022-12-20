package br.com.alura.clientelo.store.customer;

import br.com.alura.clientelo.store.customer.dto.CreateCustomerRequest;
import br.com.alura.clientelo.store.customer.dto.CreateCustomerRequestConverter;
import br.com.alura.clientelo.store.customer.dto.FindAllCustomersResponse;
import br.com.alura.clientelo.store.customer.dto.FindAllCustomersResponseConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    public Customer create(CreateCustomerRequest request) {
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
