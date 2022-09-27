package com.example.ecommerce.controllers;

import com.example.ecommerce.config.ApiResponse;
import com.example.ecommerce.dto.order.OrderCreateDto;
import com.example.ecommerce.dto.order.OrderGetDto;
import com.example.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@PreAuthorize("isAuthenticated()")
@Validated
@RequestMapping("/orders/")
public class OrderController {
    OrderService service;

    @Autowired
    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<OrderGetDto> createOrder(@Valid @RequestBody OrderCreateDto orderCreateDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        OrderGetDto orderGetDto = service.createOrder(orderCreateDto, username);
        return new ResponseEntity<>(orderGetDto, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Set<OrderGetDto>> getOrders() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return new ResponseEntity<>(service.getOrders(username), HttpStatus.OK);

    }

    @GetMapping("{id}")
    public ResponseEntity<OrderGetDto> getOrder(@PathVariable Integer id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return new ResponseEntity<>(service.getOrderById(id, username), HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity<ApiResponse> cancelOrder(@PathVariable Integer id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        service.cancelOrder(id, username);
        return new ResponseEntity<>(new ApiResponse(true, "Order Canceled"), HttpStatus.OK);
    }

}
