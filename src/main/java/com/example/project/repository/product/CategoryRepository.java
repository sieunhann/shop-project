package com.example.project.repository.product;

import com.example.project.entity.product.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    Optional<CategoryEntity> findByNameContainingIgnoreCase(String name);

    @Query(nativeQuery = true,
            value = "SELECT * FROM category WHERE (?1 IS NULL OR name LIKE CONCAT('%', ?1 ,'%')) " +
                    "GROUP BY id ORDER BY updated_at DESC")
    Page<CategoryEntity> findCategories(String name, Pageable pageable);

    @Modifying
    @Transactional
    @Query(nativeQuery = true,
            value = "Update category c SET c.name = :nameValue, c.description = :desValue WHERE c.id = :idValue")
    void updateCategory(@Param("idValue") Long id,
                                  @Param("nameValue") String name,
                                  @Param("desValue") String des);

    @Override
    @Transactional
    void delete(CategoryEntity entity);

}
