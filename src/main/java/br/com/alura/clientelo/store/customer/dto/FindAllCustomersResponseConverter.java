package br.com.alura.clientelo.store.customer.dto;

import br.com.alura.clientelo.store.core.entity.customer.Customer;

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
