package com.example.ecommerce.service;

import com.example.ecommerce.dto.authorization.RegisterDto;
import com.example.ecommerce.dto.product.ProductsGetDto;
import com.example.ecommerce.exceptions.NotFoundException;
import com.example.ecommerce.model.ERole;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.Role;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.repository.RoleRepository;
import com.example.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    UserRepository userRepository;
    ProductRepository productRepository;
    PasswordEncoder encoder;
    RoleRepository roleRepository;

    public void createUser(RegisterDto signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new RuntimeException("Username already taken");
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new RuntimeException("Email is already in use!");
        }
        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getName(),
                signUpRequest.getSurname());

        Set<Role> roles = new HashSet<>();
        Optional<Role> userRole = roleRepository.findByName(ERole.ROLE_USER);
        roles.add(userRole.get());
        user.setRoles(roles);
        userRepository.save(user);
    }

    public void createUser(User user) {
        System.out.println("XD");
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already taken");
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email is already in use!");
        }
        // Create new user's account
        user.setPassword(encoder.encode(user.getPassword()));
        Optional<Role> userRole = roleRepository.findByName(ERole.ROLE_USER);
        user.getRoles().add(userRole.get());
        userRepository.save(user);
        System.out.println("omaga");
    }

    public Set<ProductsGetDto> getFavorite(String username) {
        User user = userRepository.findByUsername(username).get(); // if authenticated, you can assume the user exists
        Set<ProductsGetDto> values = new HashSet<>();
        user.getFavorite().forEach(product -> values.add(new ProductsGetDto(product, true)));
        return values;
    }

    public void addFavorite(String username, Integer product_id) {
        User user = userRepository.findByUsername(username).get();
        Optional<Product> product = productRepository.findById(product_id);
        if (product.isEmpty()) {
            throw new NotFoundException("Product with id " + product_id + " doesnt exist");
        }
        user.addFavorite(product.get());
        userRepository.save(user);
    }

    public void deleteFavorite(String username, Integer product_id) {
        User user = userRepository.findByUsername(username).get();
        Optional<Product> product = productRepository.findById(product_id);
        if (product.isEmpty()) {
            throw new NotFoundException("Product with id " + product_id + " doesnt exist");
        }
        user.deleteFavorite(product.get());
        userRepository.save(user);
    }

    @Autowired
    public UserService(UserRepository userRepository, ProductRepository productRepository, PasswordEncoder encoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.encoder = encoder;
        this.roleRepository = roleRepository;
    }

}
