package com.example.ecommerce.dto.review;

import com.example.ecommerce.model.Review;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class ReviewGetDto {
    @NotNull String name;
    @NotNull Date postDate;
    @NotNull String review;
    @NotNull Double rating;

    public ReviewGetDto() {
    }

    public ReviewGetDto(Review review) {
        this.name = review.getUser().getName() + " " + review.getUser().getSurname();
        this.postDate = Date.from(review.getCreatedAt());
        this.review = review.getReview();
        this.rating = review.getRating();
    }

    public ReviewGetDto(String name, Date postDate, String review, Double rating) {
        this.name = name;
        this.postDate = postDate;
        this.review = review;
        this.rating = rating;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
