package com.example.ecommerce.dto.address;

import com.example.ecommerce.model.DeliveryAddress;

public class AddressUpdateDto {
    private String name;
    private String streetLine;
    private String phoneNumber;
    private String city;
    private String zipCode;

    public AddressUpdateDto() {
    }

    public AddressUpdateDto(String name, String streetLine, String phoneNumber, String city, String zipCode) {
        this.name = name;
        this.streetLine = streetLine;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.zipCode = zipCode;
    }
    public AddressUpdateDto(DeliveryAddress address) {
        this.name = address.getName();
        this.streetLine = address.getStreetLine();
        this.phoneNumber = address.getPhoneNumber();
        this.city = address.getCity();
        this.zipCode = address.getZipCode();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreetLine() {
        return streetLine;
    }

    public void setStreetLine(String streetLine) {
        this.streetLine = streetLine;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

}
