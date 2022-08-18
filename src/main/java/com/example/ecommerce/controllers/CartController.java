package com.example.ecommerce.controllers;

import com.example.ecommerce.dto.cart.AddToCartDto;
import com.example.ecommerce.dto.cart.CartGetDto;
import com.example.ecommerce.dto.cart.CartItemDto;
import com.example.ecommerce.exceptions.NotFoundException;
import com.example.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@PreAuthorize("isAuthenticated()")
@Validated
@RequestMapping("/cart/")
public class CartController {
    CartService service;

    @Autowired
    public CartController(CartService service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<CartItemDto> addToCart(@Valid @RequestBody AddToCartDto cart){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        return new ResponseEntity<>(service.addToCart(cart, username), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<CartGetDto> getCart(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return new ResponseEntity<>(service.getCartItems(username), HttpStatus.OK);

    }
    @PutMapping("")
    public ResponseEntity<AddToCartDto> changeQuantity(@Valid @RequestBody AddToCartDto cart ){
        if(cart.getId() == null){
            throw new NotFoundException("Cart item not found");
        }
        service.changeQuantity(cart);
        return new ResponseEntity<>(new AddToCartDto(cart.getId(), cart.getProductId(), cart.getQuantity()), HttpStatus.OK);
    }
}
