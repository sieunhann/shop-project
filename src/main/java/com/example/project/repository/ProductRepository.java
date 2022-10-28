package com.example.project.repository;

import com.example.project.dto.ProductDto;
import com.example.project.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Override
    Page<ProductEntity> findAll(Pageable pageable);


//    @Query(nativeQuery = true,
//
//            value = "SELECT * FROM product p " +
//                    "WHERE (:query IS NULL OR p.name LIKE CONCAT('%', :query ,'%'))\n" +
//                    "ORDER BY p.created_at",
//
//            countQuery = "SELECT COUNT(p.id) FROM product p " +
//                    "WHERE (:query IS NULL OR p.name LIKE CONCAT('%', :query ,'%'))"
//    )
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

}