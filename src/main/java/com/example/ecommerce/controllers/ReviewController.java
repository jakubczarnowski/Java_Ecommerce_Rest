package com.example.ecommerce.controllers;

import com.example.ecommerce.config.ApiResponse;
import com.example.ecommerce.dto.review.ReviewCreateDto;
import com.example.ecommerce.dto.review.ReviewGetDto;
import com.example.ecommerce.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping("/reviews/")
public class ReviewController {
    ReviewService service;

    @Autowired
    public ReviewController(ReviewService service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<ReviewGetDto> postReview(@Valid @RequestBody ReviewCreateDto review) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        return new ResponseEntity<>(service.postReview(review, username), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> updateReview(@Valid @PathVariable Integer id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        service.deleteReview(id, username);
        return new ResponseEntity<>(new ApiResponse(true, "Deleted successfully"), HttpStatus.OK);
    }
}