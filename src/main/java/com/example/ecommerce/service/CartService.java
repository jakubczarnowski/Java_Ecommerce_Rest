package com.example.ecommerce.service;

import com.example.ecommerce.dto.cart.AddToCartDto;
import com.example.ecommerce.dto.cart.CartGetDto;
import com.example.ecommerce.dto.cart.CartItemDto;
import com.example.ecommerce.dto.cart.ChangeQuantityDto;
import com.example.ecommerce.exceptions.AlreadyInCartError;
import com.example.ecommerce.exceptions.NotFoundException;
import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.CartRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    CartRepository cartRepository;
    UserRepository userRepository;
    ProductRepository productRepository;

    public CartItemDto addToCart(AddToCartDto addToCartDto, String username) {
        Optional<User> user = userRepository.findByUsername(username);
        Optional<Product> product = productRepository.findById(addToCartDto.getProductId());
        if (product.isEmpty()) {
            throw new NotFoundException("Product with id " + addToCartDto.getProductId() + " doesn't exist");
        }
        if (cartRepository.existsByProductAndUserAndActiveTrue(product.get(), user.get())) {
            throw new AlreadyInCartError("Product already in cart. Change the quantity");
        }
        Cart cart = new Cart(product.get(), user.get(), addToCartDto.getQuantity());
        cartRepository.save(cart);
        return new CartItemDto(cart);
    }

    @Autowired
    public CartService(CartRepository cartRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public CartGetDto getCartItems(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        List<Cart> cartItems = cartRepository.findAllByUserId(user.get().getId());
        CartGetDto cart = new CartGetDto();
        for (Cart item :
                cartItems) {
            cart.addCartItem(new CartItemDto(item));
        }
        cart.calculateTotalCost();
        return cart;
    }

    public void changeQuantity(ChangeQuantityDto cartDto) {
        Optional<Cart> cart = cartRepository.findById(cartDto.getId());
        if (cart.isEmpty()) {
            throw new NotFoundException("Cart with id " + cartDto.getId() + " not found");
        }
        if (cartDto.getQuantity() == 0) {
            cartRepository.delete(cart.get());
            return;
        }
        cart.get().setQuantity(cartDto.getQuantity());
        cartRepository.save(cart.get());
    }
}
