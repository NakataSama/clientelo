package br.com.alura.clientelo.store.adapter.controller.customer.dto;

public class FindAllCustomersResponse {

    private String name;
    private String documentNumber;
    private String phone;
    private String locale;

    public FindAllCustomersResponse(String name, String documentNumber, String phone, String locale) {
        this.name = name;
        this.documentNumber = documentNumber;
        this.phone = phone;
        this.locale = locale;
    }

    public String getName() {
        return name;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public String getPhone() {
        return phone;
    }

    public String getLocale() {
        return locale;
    }
}
