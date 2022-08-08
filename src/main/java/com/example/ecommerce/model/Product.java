package com.example.ecommerce.model;

import com.example.ecommerce.model.converter.StringListConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "products")

public class Product extends BaseEntity {
    private @NotNull String name;
    @Convert(converter = StringListConverter.class)
    private @NotNull List<String> imagesUrl;
    private @NotNull String description;
    private @NotNull Double price;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    Category category;

    @ManyToMany(mappedBy="favorite")
    @JsonIgnore
    private Set<User> userFavorite = new HashSet<User>();

    public Product(String name, List<String> imagesUrl, String description, Category category, Double price) {
        super();
        this.name = name;
        this.imagesUrl = imagesUrl;
        this.description = description;
        this.category = category;
        this.price = price;
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
    public void addFavorite(User user){
        this.userFavorite.add(user);
    }
    public void removeFavorite(User user){
        this.userFavorite.remove(user);
    }
    public void setUserFavorite(Set<User> userFavorite) {
        this.userFavorite = userFavorite;
    }


    @Override
    public String toString() {
        return (this.name + this.description).toLowerCase();
    }

}
