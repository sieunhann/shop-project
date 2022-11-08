package com.example.project.repository.order;

import com.example.project.entity.order.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long> {

    @Transactional
    @Modifying
    @Query(nativeQuery = true,
            value = "UPDATE cart SET note = :note WHERE id = :id")
    public void updateCart(Long id, String note);
}