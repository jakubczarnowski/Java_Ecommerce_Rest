package com.example.ecommerce.dto.order;

import com.example.ecommerce.model.User;

import javax.validation.constraints.NotNull;

public class OrderCreateDto {
    private @NotNull Integer deliveryAddressId;
    private String moreInfo;

    public OrderCreateDto() {
    }

    public OrderCreateDto(Integer deliveryAddressId, String moreInfo) {
        this.deliveryAddressId = deliveryAddressId;
        this.moreInfo = moreInfo;
    }

    public Integer getDeliveryAddressId() {
        return deliveryAddressId;
    }

    public void setDeliveryAddressId(Integer deliveryAddressId) {
        this.deliveryAddressId = deliveryAddressId;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }
}
