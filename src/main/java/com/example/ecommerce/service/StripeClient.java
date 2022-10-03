package com.example.ecommerce.service;

import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.repository.UserRepository;
import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Component
public class StripeClient {
    UserRepository userRepository;
    OrderRepository orderRepository;
    @Value("${ecommerce.app.stripe.privateKey}")
    private String API_SECRET_KEY;

    @Autowired
    public StripeClient(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    public String chargeNewCard(String username, Integer order_id) throws Exception {
        Stripe.apiKey = API_SECRET_KEY;
        User user = userRepository.findByUsername(username).get();
        Optional<Order> order = orderRepository.findById(order_id);
        if (order.isEmpty()) {
            throw new EntityNotFoundException("Order Not Found");
        }

        Double amount = order.get().getTotalCost();
        PaymentIntentCreateParams params =
                PaymentIntentCreateParams.builder()
                        .putMetadata("order_id", String.valueOf(order_id))
                        .setAmount(amount.longValue() * 100)
                        .setCurrency("usd")
                        .setAutomaticPaymentMethods(
                                PaymentIntentCreateParams.AutomaticPaymentMethods
                                        .builder()
                                        .setEnabled(true)
                                        .build()
                        )
                        .build();

        // Create a PaymentIntent with the order amount and currency
        PaymentIntent paymentIntent = PaymentIntent.create(params);
        System.out.println(paymentIntent);
        return paymentIntent.getClientSecret();
    }

}
