package com.example.ecommerce.service;

import com.example.ecommerce.Utils.Utilities;
import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.CartRepository;
import com.example.ecommerce.repository.UserRepository;
import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StripeClient {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    UserRepository userRepository;
    @Value("${ecommerce.app.stripe.privateKey}")
    private String API_SECRET_KEY;

    public StripeClient() {
    }

    public String chargeNewCard(String username) throws Exception {
        Stripe.apiKey = API_SECRET_KEY;
        User user = userRepository.findByUsername(username).get();
        List<Cart> cartItems = cartRepository.findAllByUserId(user.getId());
        Double amount = Utilities.calculateTotalCartCost(cartItems);
        PaymentIntentCreateParams params =
                PaymentIntentCreateParams.builder()
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

        return paymentIntent.getClientSecret();
    }

}
