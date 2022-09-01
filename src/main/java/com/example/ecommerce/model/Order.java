package com.example.ecommerce.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name="orders")
public class Order extends BaseEntity{

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private @NotNull User user;

    @OneToMany
    private @NotNull Set<Cart> cartItems;
    private @NotNull Double totalCost;
    private @NotNull EPaymentStatus paymentStatus = EPaymentStatus.PROCESSING;
    @ManyToOne
    @JoinColumn(name="delivery_id")
    private @NotNull DeliveryAddress deliveryAddress;
    private @NotNull String moreInfo;

    public Order() {
    }

    public Order(User user, Set<Cart> cartItems, Double totalCost, EPaymentStatus paymentStatus, DeliveryAddress deliveryAddress, String moreInfo) {
        this.user = user;
        this.cartItems = cartItems;
        this.totalCost = totalCost;
        this.paymentStatus = paymentStatus;
        this.deliveryAddress = deliveryAddress;
        this.moreInfo = moreInfo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Cart> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Set<Cart> cartItems) {
        this.cartItems = cartItems;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public EPaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(EPaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public DeliveryAddress getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(DeliveryAddress deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }
}
