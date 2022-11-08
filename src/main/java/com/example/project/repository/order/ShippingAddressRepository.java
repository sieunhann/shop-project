package com.example.project.repository.order;

import com.example.project.entity.order.OrderEntity;
import com.example.project.entity.order.ShippingAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingAddressRepository extends JpaRepository<ShippingAddressEntity, Long> {

    ShippingAddressEntity findByOrderEntity(OrderEntity order);
}