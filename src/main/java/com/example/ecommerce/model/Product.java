package com.example.ecommerce.model;

import com.example.ecommerce.Utils.GenerateRandomString;
import com.example.ecommerce.model.converter.StringListConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.Normalizer;
import java.util.*;
import java.util.regex.Pattern;

@Entity
@Table(name = "products")
@SQLDelete(sql = "UPDATE products SET active = false WHERE id = ?")
@Where(clause = "active = 1")
public class Product extends BaseEntity {
    private @NotNull String name;
    @Convert(converter = StringListConverter.class)
    private @NotNull List<String> imagesUrl;
    private @NotNull String description;
    private @NotNull Double price;
    private @NotNull String slug;

    @Nullable
    @OneToMany(fetch = FetchType.LAZY)
    private List<Review> reviews = new ArrayList<>();
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    Category category;

    @ManyToMany(mappedBy = "favorite", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<User> userFavorite = new HashSet<User>();

    private Boolean active = true;

    public Product(String name, List<String> imagesUrl, String description, Category category, Double price, List<Review> reviews) {
        super();
        this.name = name;
        this.imagesUrl = imagesUrl;
        this.description = description;
        this.category = category;
        this.price = price;
        this.reviews = reviews;
        this.generateSlug(name);
    }

    public Product() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public void addImage(String imageUrl) {
        this.imagesUrl.add(imageUrl);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Set<User> getUserFavorite() {
        return userFavorite;
    }

    public void addFavorite(User user) {
        this.userFavorite.add(user);
    }

    public void removeFavorite(User user) {
        this.userFavorite.remove(user);
    }

    public void setUserFavorite(Set<User> userFavorite) {
        this.userFavorite = userFavorite;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public void generateSlug(String name) {
        Pattern NONLATIN = Pattern.compile("[^\\w-]");
        Pattern WHITESPACE = Pattern.compile("[\\s]");
        String nowhitespace = WHITESPACE.matcher(name).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("") + "-" + GenerateRandomString.generateRandomString(6);
        setSlug(slug.toLowerCase(Locale.ENGLISH));
    }

    @Override
    public String toString() {
        return (this.name + this.description).toLowerCase();
    }


    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    public void deleteReview(Review review) {
        reviews.remove(review);
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Double getAverageRating() {
        double sum = 0d;
        for (Review review :
                this.reviews) {
            sum += review.rating;
        }
        System.out.println(sum);
        System.out.println(this.getReviews().size());
        if (this.getReviews().size() == 0) {
            return 0d;
        }
        return sum / this.reviews.size();
    }

    public Integer getReviewsCount() {
        return this.reviews.size();
    }
}
