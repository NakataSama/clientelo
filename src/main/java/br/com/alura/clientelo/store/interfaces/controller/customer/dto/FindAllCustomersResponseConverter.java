package br.com.alura.clientelo.store.interfaces.controller.customer.dto;

import br.com.alura.clientelo.store.domain.customer.Customer;

public class FindAllCustomersResponseConverter {

    public FindAllCustomersResponse from(Customer customer) {
        return new FindAllCustomersResponse(
                customer.getName(),
                customer.getDocumentNumber(),
                customer.getPhone(),
                "%s/%s".formatted(customer.getAddress().getCity(), customer.getAddress().getState())
        );
    }
}
