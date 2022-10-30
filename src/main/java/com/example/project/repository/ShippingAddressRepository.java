package com.example.project.repository;

import com.example.project.entity.OrderEntity;
import com.example.project.entity.ShippingAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingAddressRepository extends JpaRepository<ShippingAddressEntity, Long> {

    ShippingAddressEntity findByOrderEntity(OrderEntity order);
}