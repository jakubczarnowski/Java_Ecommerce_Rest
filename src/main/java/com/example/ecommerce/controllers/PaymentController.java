package com.example.ecommerce.controllers;

import com.example.ecommerce.model.EPaymentStatus;
import com.example.ecommerce.service.OrderService;
import com.example.ecommerce.service.StripeClient;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.model.EventDataObjectDeserializer;
import com.stripe.model.StripeObject;
import com.stripe.net.Webhook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Validated
@RequestMapping("/payment")
public class PaymentController {
    private final StripeClient stripeClient;
    private final OrderService orderService;
    @Value("${ecommerce.app.stripe.webhook}")
    String endpointSecret;

    @Autowired
    public PaymentController(StripeClient stripeClient, OrderService orderService) {
        this.stripeClient = stripeClient;
        this.orderService = orderService;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/charge")
    public String chargeCard(@RequestBody Integer order_id) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return this.stripeClient.chargeNewCard(username, order_id);
    }

    @PostMapping(value = "/webhook")
    public ResponseEntity<String> confirmPayment(@RequestBody String payload, @RequestHeader("Stripe-Signature") String sigHeader) {
        Event event = null;

        try {
            event = Webhook.constructEvent(
                    payload, sigHeader, endpointSecret
            );
        } catch (SignatureVerificationException e) {
            // Invalid signature
            return new ResponseEntity<>("Invalid Signature", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        // Deserialize the nested object inside the event
        EventDataObjectDeserializer dataObjectDeserializer = event.getDataObjectDeserializer();
        StripeObject stripeObject = null;
        if (dataObjectDeserializer.getObject().isPresent()) {
            stripeObject = dataObjectDeserializer.getObject().get();
        } else {
            // Deserialization failed, probably due to an API version mismatch.
            // Refer to the Javadoc documentation on `EventDataObjectDeserializer` for
            // instructions on how to handle this case, or return an error here.
        }
        System.out.println(payload);
        // Handle the event
        switch (event.getType()) {
            case "payment_intent.succeeded": {
                System.out.println("SUCCEED");
                // Then define and call a function to handle the event payment_intent.succeeded
                break;
            }
            case "charge.succeeded": {
                GsonJsonParser parser = new GsonJsonParser();
                Map<String, Object> map = parser.parseMap(dataObjectDeserializer.getObject().get().toJson());
                // mess
                Integer order_id = Integer.parseInt(map.get("metadata").toString().split("=")[1].toString().replace("}", ""));
                this.orderService.changePaymentStatus(EPaymentStatus.FINISHED, order_id);
                break;
            }
            // ... handle other event types
            default:
                System.out.println("Unhandled event type: " + event.getType());
        }
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
