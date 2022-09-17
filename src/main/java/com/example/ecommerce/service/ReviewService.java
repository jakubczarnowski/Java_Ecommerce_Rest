package com.example.ecommerce.service;

import com.example.ecommerce.dto.review.ReviewCreateDto;
import com.example.ecommerce.dto.review.ReviewGetDto;
import com.example.ecommerce.exceptions.NotFoundException;
import com.example.ecommerce.exceptions.UserNotValidException;
import com.example.ecommerce.model.ERole;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.Review;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.repository.ReviewRepository;
import com.example.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public ReviewGetDto postReview(ReviewCreateDto reviewCreateDto, String username) {
        User user = userRepository.findByUsername(username).get();
        Optional<Product> product = productRepository.findById(reviewCreateDto.getProductId());
        if (product.isEmpty()) {
            throw new NotFoundException("Product not found");
        }
        Review review = reviewRepository.save(new Review(user, reviewCreateDto.getRating(), reviewCreateDto.getReview()));
        product.get().addReview(review);
        productRepository.save(product.get());
        return new ReviewGetDto(review);
    }

    public void deleteReview(Integer id, String username) {
        User user = userRepository.findByUsername(username).get();
        Optional<Review> review = reviewRepository.findById(id);
        if (review.isEmpty()) {
            throw new NotFoundException("Review not found");
        }
        // User can't delete anothers person user, admin can delete all of them.
        if (review.get().getUser() != user && !user.getRoles().stream().anyMatch(role -> role.getName() == ERole.ROLE_ADMIN)) {
            throw new UserNotValidException("Review wasn't written by this user");
        }
        reviewRepository.delete(review.get());
    }
}
