package com.example.ecommerce.repository;

import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CartRepository extends JpaRepository<Cart, Integer> {
    @Query("select c from Cart c where c.active=true and c.user.id=:id")
    List<Cart> findAllByUserId(@Param("id") Integer id);

    @Modifying
    @Query("update Cart c set c.active=false where c.user.id =:id")
    void deleteAllByUserId(@Param("id") Integer id);


    Boolean existsByProductAndUserAndActiveTrue(Product product, User user);

    void deleteAllByProduct(Product product);
}
