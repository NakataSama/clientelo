package br.com.alura.clientelo.store.customer;

import java.util.Objects;

public class Address {

    private String street;
    private String number;
    private String additionalInfo;
    private String district;
    private String city;
    private String state;

    public Address() {}

    public Address(String street, String number, String additionalInfo, String district, String city, String state) {
        this.street = street;
        this.number = number;
        this.additionalInfo = additionalInfo;
        this.district = district;
        this.city = city;
        this.state = state;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(street, address.street) && Objects.equals(number, address.number) && Objects.equals(additionalInfo, address.additionalInfo) && Objects.equals(district, address.district) && Objects.equals(city, address.city) && Objects.equals(state, address.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, number, additionalInfo, district, city, state);
    }
}
