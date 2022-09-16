package com.example.ecommerce.dto.cart;

import javax.validation.constraints.NotNull;

public class ChangeQuantityDto {
    private @NotNull Integer id;
    private @NotNull Integer quantity;

    public ChangeQuantityDto() {
    }


    public ChangeQuantityDto(Integer id, Integer quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartDto{" +
                "id=" + id +
                ", quantity=" + quantity +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
