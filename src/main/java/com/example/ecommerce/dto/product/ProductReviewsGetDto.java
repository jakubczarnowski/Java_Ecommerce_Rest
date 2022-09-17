package com.example.ecommerce.dto.product;


import com.example.ecommerce.dto.review.ReviewGetDto;
import com.example.ecommerce.model.BaseEntity;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.Review;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class ProductReviewsGetDto extends BaseEntity {
    private @NotNull String name;
    private @NotNull List<String> imagesUrl;
    private @NotNull double price;
    private @NotNull String description;
    private @NotNull Integer categoryId;
    private @NotNull String categoryName;
    private @NotNull Boolean isFavorite;
    private @NotNull Double rating;
    private @NotNull Integer ratingCount;
    private @NotNull String slug;
    private @NotNull List<ReviewGetDto> reviews = new ArrayList<>();


    // ProductDto from product
    public ProductReviewsGetDto(Product product, Boolean isFavorite) {
        this.id = product.getId();
        this.createdAt = product.getCreatedAt();
        this.modifiedAt = product.getModifiedAt();
        this.name = product.getName();
        this.imagesUrl = product.getImagesUrl();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.categoryId = product.getCategory().getId();
        this.categoryName = product.getCategory().getCategoryName();
        this.isFavorite = isFavorite;
        this.slug = product.getSlug();
        this.rating = product.getAvarageRating();
        this.ratingCount = product.getReviews().size();
        for (Review review :
                product.getReviews()) {
            this.addReview(review);
        }
    }

    public ProductReviewsGetDto(Integer id, String name, List<String> imagesUrl, double price, String description, Integer categoryId, String categoryName, Boolean isFavorite) {
        this.id = id;
        this.name = name;
        this.imagesUrl = imagesUrl;
        this.price = price;
        this.description = description;
        this.categoryId = categoryId;
        this.isFavorite = isFavorite;
        this.categoryName = categoryName;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getImagesUrl() {
        return imagesUrl;
    }

    public void setImagesUrl(List<String> imagesUrl) {
        this.imagesUrl = imagesUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Boolean getFavorite() {
        return isFavorite;
    }

    public void setFavorite(Boolean favorite) {
        isFavorite = favorite;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Integer getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(Integer ratingCount) {
        this.ratingCount = ratingCount;
    }

    public void addReview(Review review) {
        this.reviews.add(new ReviewGetDto(review));
    }

    public void deleteReview(ReviewGetDto review) {
        this.reviews.remove(review);
    }

    public List<ReviewGetDto> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewGetDto> reviews) {
        this.reviews = reviews;
    }
}
