package br.com.alura.clientelo.store.interfaces.controller.customer.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateCustomerRequest {

    @NotNull
    @Size(min = 2)
    private String name;

    @NotNull
    @Size(min = 2)
    private String documentNumber;

    @NotNull
    @Size(min = 2)
    private String phone;

    @NotNull
    @Size(min = 2)
    private String street;

    @NotNull
    @Size(min = 1)
    private String number;

    @Nullable
    private String additionalInfo;

    @NotNull
    @Size(min = 2)
    private String district;

    @NotNull
    @Size(min = 2)
    private String city;

    @NotNull
    @Size(min = 2)
    private String state;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 3)
    private String password;


    public CreateCustomerRequest(String name, String documentNumber, String phone, String street, String number, String additionalInfo, String district, String city, String state, String email, String password) {
        this.name = name;
        this.documentNumber = documentNumber;
        this.phone = phone;
        this.street = street;
        this.number = number;
        this.additionalInfo = additionalInfo;
        this.district = district;
        this.city = city;
        this.state = state;
        this.email = email;
        this.password = password;
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

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public String getDistrict() {
        return district;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
