package com.example.ecommerce.Utils;

import com.example.ecommerce.model.Cart;

import java.util.Set;

public class Utilities {
    static public Double calculateTotalCartCost(Set<Cart> cartItems) {
        Double totalCost = 0.0;
        for (Cart item :
                cartItems) {
            totalCost += item.getQuantity() * item.getProduct().getPrice();
        }
        return totalCost;
    }
}
