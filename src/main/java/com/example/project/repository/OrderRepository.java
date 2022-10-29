package com.example.project.repository;

import com.example.project.dto.OrderDto;
import com.example.project.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, String> {
    @Query(nativeQuery = true, name = "getOrderDtos", countName = "getOrderDtos.count")
    Page<OrderDto> getOrderDtosPage(@Param("query") String query, Pageable pageable);
}