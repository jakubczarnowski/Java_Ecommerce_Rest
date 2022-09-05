package com.example.ecommerce.repository;

import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

@Repository
@Transactional
public interface OrderRepository extends JpaRepository<Order, Integer> {
    Set<Order> findAllByUser(User user);

    Optional<Order> findByUserAndId(User user, Integer id);
}
