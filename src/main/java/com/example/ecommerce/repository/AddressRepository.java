package com.example.ecommerce.repository;

import com.example.ecommerce.model.DeliveryAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<DeliveryAddress, Integer> {
}
