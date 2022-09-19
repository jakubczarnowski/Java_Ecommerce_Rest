package com.example.ecommerce.dto.cart;

import com.example.ecommerce.dto.product.ProductsGetDto;
import com.example.ecommerce.model.Cart;

import javax.validation.constraints.NotNull;

public class CartItemDto {
    private Integer id;
    private @NotNull Integer quantity;
    private @NotNull ProductsGetDto product;

    public CartItemDto(Integer id, Integer quantity, ProductsGetDto product) {
        this.id = id;
        this.quantity = quantity;
        this.product = product;
    }

    public CartItemDto(Cart cart) {
        this.id = cart.getId();
        this.quantity = cart.getQuantity();
        this.product = new ProductsGetDto(cart.getProduct(), false);
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

    public ProductsGetDto getProduct() {
        return product;
    }

    public void setProduct(ProductsGetDto product) {
        this.product = product;
    }
}
