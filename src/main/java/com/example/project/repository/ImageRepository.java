package com.example.project.repository;

import com.example.project.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, Long> {
    ImageEntity findByUrl(String url);

    @Query(nativeQuery = true, value = "SELECT * FROM image i WHERE i.product_id = :id")
    List<ImageEntity> findByProduct(@Param("id") Long id);
}