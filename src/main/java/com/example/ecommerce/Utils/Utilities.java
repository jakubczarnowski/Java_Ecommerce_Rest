package com.example.ecommerce.Utils;

import com.example.ecommerce.model.Cart;

import java.util.List;

public class Utilities {
    static public Double calculateTotalCartCost(List<Cart> cartItems) {
        Double totalCost = 0.0;
        for (Cart item :
                cartItems) {
            totalCost += item.getQuantity() * item.getProduct().getPrice();
        }
        return totalCost;
    }
}
