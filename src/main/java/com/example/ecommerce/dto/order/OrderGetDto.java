package com.example.ecommerce.dto.order;

import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.DeliveryAddress;
import com.example.ecommerce.model.EPaymentStatus;
import com.example.ecommerce.model.Order;

import java.util.Date;
import java.util.Set;

public class OrderGetDto {
    private Integer id;
    private Set<Cart> cartItems;
    private Double totalCost;
    private EPaymentStatus paymentStatus;
    private DeliveryAddress deliveryAddress;
    private String moreInfo;
    private Boolean active;
    private Date timeOrdered;
    private EPaymentStatus status;

    public OrderGetDto() {
    }

    @Override
    public String toString() {
        return "OrderGetDto{" +
                "id=" + id +
                ", cartItems=" + cartItems +
                ", totalCost=" + totalCost +
                ", paymentStatus=" + paymentStatus +
                ", deliveryAddress=" + deliveryAddress +
                ", moreInfo='" + moreInfo + '\'' +
                ", active=" + active +
                ", timeOrdered=" + timeOrdered +
                ", status=" + status +
                '}';
    }

    public OrderGetDto(Set<Cart> cartItems, Double totalCost, EPaymentStatus paymentStatus, DeliveryAddress deliveryAddress, String moreInfo, Boolean active, Date timeOrdered, EPaymentStatus status) {
        this.cartItems = cartItems;
        this.totalCost = totalCost;
        this.paymentStatus = paymentStatus;
        this.deliveryAddress = deliveryAddress;
        this.moreInfo = moreInfo;
        this.active = active;
        this.timeOrdered = timeOrdered;
        this.status = status;
    }

    public OrderGetDto(Order order) {
        this.cartItems = order.getCartItems();
        this.totalCost = order.getTotalCost();
        this.paymentStatus = order.getPaymentStatus();
        this.deliveryAddress = order.getDeliveryAddress();
        this.active = order.getActive();
        this.status = order.getPaymentStatus();
        this.timeOrdered = Date.from(order.getCreatedAt());
        this.id = order.getId();
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getTimeOrdered() {
        return timeOrdered;
    }

    public void setTimeOrdered(Date timeOrdered) {
        this.timeOrdered = timeOrdered;
    }

    public EPaymentStatus getStatus() {
        return status;
    }

    public void setStatus(EPaymentStatus status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
