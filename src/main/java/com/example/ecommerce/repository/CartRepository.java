package com.example.ecommerce.repository;

import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findAllByUser(User user);
    Boolean existsByProductAndUser(Product product, User user);
    void deleteAllByProduct(Product product);
}
