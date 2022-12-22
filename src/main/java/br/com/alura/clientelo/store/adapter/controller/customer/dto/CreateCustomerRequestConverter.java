package br.com.alura.clientelo.store.adapter.controller.customer.dto;

import br.com.alura.clientelo.store.core.entity.customer.Address;
import br.com.alura.clientelo.store.core.entity.customer.Customer;

public class CreateCustomerRequestConverter {
    public Customer toCustomer(CreateCustomerRequest request) {
        return new Customer(
                request.getName(),
                request.getDocumentNumber(),
                request.getPhone(),
                new Address(
                        request.getStreet(),
                        request.getNumber(),
                        request.getAdditionalInfo(),
                        request.getDistrict(),
                        request.getCity(),
                        request.getState()
                ),
                request.getEmail(),
                request.getPassword()
        );
    }
}
