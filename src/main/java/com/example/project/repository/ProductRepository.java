package com.example.project.repository;

import com.example.project.dto.ProductDto;
import com.example.project.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Override
    Page<ProductEntity> findAll(Pageable pageable);

    @Query(nativeQuery = true,

            value = "SELECT p.id, p.name, p.description, p.content, p.created_at, p.updated_at \n" +
                    "FROM product p LEFT JOIN variant v ON p.id = v.product_id\n" +
                    "WHERE (:query IS NULL OR p.name LIKE CONCAT('%', :query ,'%'))\n" +
                    "OR (:query IS NULL OR v.sku LIKE CONCAT('%', :query ,'%')) GROUP BY p.id ORDER BY p.created_at DESC",

            countQuery = "SELECT COUNT(p.id) \n" +
                        "FROM product p LEFT JOIN variant v ON p.id = v.product_id\n" +
                        "WHERE (:query IS NULL OR p.name LIKE CONCAT('%', :query ,'%'))\n" +
                        "OR (:query IS NULL OR v.sku LIKE CONCAT('%', :query ,'%')) GROUP BY p.id"
    )
    Page<ProductEntity> findByName(@Param("query") String query, Pageable pageable);

    @Query(nativeQuery = true,
            value = "SELECT * FROM product p LEFT JOIN product_category pc ON p.id = pc.product_id " +
                    "WHERE (:cateId IS NULL OR pc.category_id = :cateId) ORDER BY p.created_at DESC LIMIT :count")
    List<ProductEntity> findNewProductsByCategory(@Param("cateId") Long cateId, int count);

    @Query(nativeQuery = true,

            value = "SELECT p.id, p.name, p.content, p.description, p.created_at, p.updated_at FROM product p " +
                    "INNER JOIN product_category pc ON p.id = pc.product_id INNER JOIN variant v ON p.id = v.product_id " +
                    "WHERE (:cateId IS NULL OR pc.category_id = :cateId) AND (:color IS NULL OR v.color LIKE :color) " +
                    "AND (:size IS NULL OR v.size LIKE :size) AND (:minPrice IS NULL OR v.price > :minPrice)" +
                    "AND (:maxPrice IS NULL OR v.price < :maxPrice) GROUP BY p.id ORDER BY p.created_at DESC ",

            countQuery = "SELECT COUNT(p.id) FROM product p INNER JOIN product_category pc ON p.id = pc.product_id " +
                    "INNER JOIN variant v ON p.id = v.product_id " +
                    "WHERE (:cateId IS NULL OR pc.category_id = :cateId) AND (:color IS NULL OR v.color LIKE :color) " +
                    "AND (:size IS NULL OR v.size LIKE :size) AND (:minPrice IS NULL OR v.price > :minPrice)" +
                    "AND (:maxPrice IS NULL OR v.price < :maxPrice) GROUP BY p.id ORDER BY p.created_at DESC ")
    Page<ProductEntity> findByCategoryPage(@Param("cateId") Long cateId,
                                           @Param("color") String color,
                                           @Param("size") String size,
                                           @Param("minPrice") Double minPrice,
                                           @Param("maxPrice") Double maxPrice,
                                           Pageable pageable);
}