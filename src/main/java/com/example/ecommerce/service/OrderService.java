package com.example.ecommerce.service;

import com.example.ecommerce.Utils.Utilities;
import com.example.ecommerce.dto.order.OrderCreateDto;
import com.example.ecommerce.dto.order.OrderGetDto;
import com.example.ecommerce.exceptions.NotFoundException;
import com.example.ecommerce.exceptions.UserNotValidException;
import com.example.ecommerce.model.*;
import com.example.ecommerce.repository.AddressRepository;
import com.example.ecommerce.repository.CartRepository;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class OrderService {
    UserRepository userRepository;
    CartRepository cartRepository;
    AddressRepository addressRepository;
    OrderRepository orderRepository;

    @Autowired
    public OrderService(UserRepository userRepository, CartRepository cartRepository, AddressRepository addressRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.addressRepository = addressRepository;
        this.orderRepository = orderRepository;
    }

    public OrderGetDto createOrder(OrderCreateDto orderCreateDto, String username) {
        User user = userRepository.findByUsername(username).get();
        Optional<DeliveryAddress> deliveryAddress = addressRepository.findById(orderCreateDto.getDeliveryAddressId());
        if (deliveryAddress.isEmpty()) {
            throw new NotFoundException("Delivery Address not found");
        }
        if (!user.getAddresses().contains(deliveryAddress.get())) {
            throw new UserNotValidException("Address doesnt belong to the user");
        }
        List<Cart> cartItems = cartRepository.findAllByUserId(user.getId());
        Order order = new Order(user, Set.copyOf(cartItems), Utilities.calculateTotalCartCost(cartItems), EPaymentStatus.PROCESSING, deliveryAddress.get(), orderCreateDto.getMoreInfo());
        orderRepository.save(order);
        cartRepository.deleteAllByUserId(user.getId());
        return new OrderGetDto(order);
    }

    public Set<OrderGetDto> getOrders(String username) {
        User user = userRepository.findByUsername(username).get();
        Set<Order> orderSet = orderRepository.findAllByUser(user);
        Set<OrderGetDto> orderGetDtos = new HashSet<>();
        for (Order order :
                orderSet) {
            orderGetDtos.add(new OrderGetDto(order));
        }
        return orderGetDtos;
    }

    public OrderGetDto getOrderById(Integer id, String username) {
        User user = userRepository.findByUsername(username).get();
        Optional<Order> order = orderRepository.findByUserAndId(user, id);
        if (order.isEmpty()) {
            throw new NotFoundException("Order not found");
        }
        return new OrderGetDto(order.get());
    }

    public void cancelOrder(Integer id, String username) {
        User user = userRepository.findByUsername(username).get();
        Optional<Order> order = orderRepository.findByUserAndId(user, id);
        if (order.isEmpty()) {
            throw new NotFoundException("Order not found");
        }
        order.get().setActive(false);
        orderRepository.save(order.get());
    }
}
