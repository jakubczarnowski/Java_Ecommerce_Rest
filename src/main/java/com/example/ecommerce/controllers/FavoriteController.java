package com.example.ecommerce.controllers;

import com.example.ecommerce.config.ApiResponse;
import com.example.ecommerce.dto.product.ProductsGetDto;
import com.example.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("/favorite")
public class FavoriteController {
    UserService userService;

    @GetMapping
    public ResponseEntity<Set<ProductsGetDto>> getFavorite() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return new ResponseEntity<>(userService.getFavorite(username), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addFavorite(
            @RequestParam(required = true, name = "product_id") Integer product_id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        userService.addFavorite(username, product_id);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Favorite product added Successfully"), HttpStatus.OK);
    }
    @DeleteMapping("/{product_id}")
    public ResponseEntity<ApiResponse> deleteFavorite(
            @PathVariable Integer product_id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        userService.deleteFavorite(username, product_id);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Favorite product deleted Successfully"), HttpStatus.OK);
    }
    @Autowired
    public FavoriteController(UserService service) {
        this.userService = service;
    }
}