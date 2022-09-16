package com.example.ecommerce.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "reviews")
public class Review extends BaseEntity {
    @ManyToOne(cascade = CascadeType.PERSIST)
    @NotNull User user;
    @NotNull Double rating;
    @NotNull String review;

    public Review() {
    }

    public Review(User user, Double rating, String review) {
        this.user = user;
        this.rating = rating;
        this.review = review;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
