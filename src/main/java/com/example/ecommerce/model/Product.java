package com.example.ecommerce.model;


import com.example.ecommerce.model.converter.StringListConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name="products")
public class Product extends BaseEntity {
    private @NotNull String name;
    private @NotNull double price;
    private @NotNull String description;

    @Convert(converter = StringListConverter.class)
    private @NotNull List<String> imagesUrl;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private @NotNull Category category;

    // Contructors

    public Product(String name, List<String> imagesUrl, double price, String description, Category category) {
        super();
        this.name = name;
        this.imagesUrl = imagesUrl;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    public Product() {

    }
    // getters setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<String> getImagesUrl() {
        return imagesUrl;
    }

    public void addImage(String imageUrl){
        this.imagesUrl.add(imageUrl);
    }

    public void setImagesUrl(List<String> imagesUrl) {
        this.imagesUrl = imagesUrl;
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

}
