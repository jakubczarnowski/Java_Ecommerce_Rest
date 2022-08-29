package com.example.ecommerce.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="deliveryAddress")
public class DeliveryAddress extends BaseEntity {
    @NotNull(message = "Pick the name of the address")
    @NotBlank(message = "Name cannot be empty")
    private String name;
    @NotNull(message = "Streetline missing")
    @NotBlank(message = "streetLine cannot be empty")
    private @NotNull String streetLine;
    @NotNull(message = "Phone number missing")
    @NotBlank(message = "phoneNumber cannot be empty")
    private @NotNull String phoneNumber;
    @NotNull(message = "City missing")
    @NotBlank(message = "city cannot be empty")
    private @NotNull String city;
    @NotNull(message = "Zip Code missing")
    @NotBlank(message = "zipCode cannot be empty")
    private @NotNull String zipCode;

    public DeliveryAddress() {
    }

    public DeliveryAddress(String name, String streetLine, String phoneNumber, String city, String zipCode) {
        this.name = name;
        this.streetLine = streetLine;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.zipCode = zipCode;
    }
    public DeliveryAddress(DeliveryAddress address) {
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

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
