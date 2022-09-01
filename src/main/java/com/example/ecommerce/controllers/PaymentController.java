package com.example.ecommerce.controllers;

import com.example.ecommerce.service.StripeClient;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("isAuthenticated()")
@Validated
@RequestMapping("/payment")
public class PaymentController {
    private StripeClient stripeClient;
    @Autowired
    public PaymentController(StripeClient stripeClient) {
        this.stripeClient = stripeClient;
    }

    @PostMapping("/charge")
    public Charge chargeCard(@RequestHeader(value="token") String token, @RequestHeader(value="amount") Double amount) throws Exception {
        System.out.println(token);
        System.out.println(amount);
        return this.stripeClient.chargeNewCard(token, amount);

    }
}
