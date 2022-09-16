package com.example.ecommerce.dto.review;

import javax.validation.constraints.NotNull;

public class ReviewCreateDto {
    @NotNull Double rating;
    @NotNull String review;
}
