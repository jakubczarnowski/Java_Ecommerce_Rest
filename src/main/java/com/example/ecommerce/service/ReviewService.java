package com.example.ecommerce.service;

import com.example.ecommerce.model.Review;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.repository.ReviewRepository;
import com.example.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    UserRepository userRepository;
    ProductRepository productRepository;
    ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(UserRepository userRepository, ProductRepository productRepository, ReviewRepository reviewRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.reviewRepository = reviewRepository;
    }

    public Review addReview(String username) {
        return new Review();
    }
}
