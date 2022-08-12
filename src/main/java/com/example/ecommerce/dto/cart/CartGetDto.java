package com.example.ecommerce.dto.cart;

import java.util.ArrayList;
import java.util.List;

public class CartGetDto {
    private List<CartItemDto> cartItems = new ArrayList<>();
    private double totalCost;

    public CartGetDto() {
    }

    public CartGetDto(List<CartItemDto> cartItems, double totalCost) {
        this.cartItems = cartItems;
        this.totalCost = totalCost;
    }

    public List<CartItemDto> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemDto> cartItems) {
        this.cartItems = cartItems;
    }

    public double getTotalCost() {
        return totalCost;
    }
    public void addCartItem(CartItemDto cartDto){
        this.cartItems.add(cartDto);
    }
    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
    public double calculateTotalCost(){
        double sum = 0;
        for (CartItemDto item:
             this.cartItems) {
            sum+= (item.getProduct().getPrice() * item.getQuantity());
        }
        setTotalCost(sum);
        return sum;
    }
}
