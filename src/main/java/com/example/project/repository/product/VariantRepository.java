package com.example.project.repository.product;

import com.example.project.dto.VariantDto;
import com.example.project.entity.product.ProductEntity;
import com.example.project.entity.product.VariantEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VariantRepository extends JpaRepository<VariantEntity, Long> {

    @Query(nativeQuery = true, name = "getVariantDtos")
    List<VariantDto> getVariantDtos(@Param("query") String query);

    @Query(nativeQuery = true, name = "getVariantDtos", countName = "getVariantDtos.count")
    Page<VariantDto> getVariantDtosPage(@Param("query") String query, Pageable pageable);
    List<VariantEntity> findByProductEntity(ProductEntity product);

    @Query(nativeQuery = true, value = "SELECT DISTINCT v.color FROM variant v ORDER BY v.color")
    List<String> getVariantColor();

    @Query(nativeQuery = true, value = "SELECT DISTINCT v.size FROM variant v ORDER BY v.size")
    List<String> getVariantSize();
}
