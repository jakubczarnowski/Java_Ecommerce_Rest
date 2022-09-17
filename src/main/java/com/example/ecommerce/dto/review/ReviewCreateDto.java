package com.example.ecommerce.dto.review;

import javax.validation.constraints.NotNull;

public class ReviewCreateDto {
    Integer id;
    @NotNull Double rating;
    @NotNull String review;
    @NotNull Integer productId;

    public ReviewCreateDto() {
    }

    public ReviewCreateDto(Double rating, String review, Integer productId) {
        this.rating = rating;
        this.review = review;
        this.productId = productId;
    }

    public ReviewCreateDto(Integer id, Double rating, String review, Integer productId) {
        this.id = id;
        this.rating = rating;
        this.review = review;
        this.productId = productId;

    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
