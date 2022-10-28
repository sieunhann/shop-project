package com.example.project;

import com.example.project.entity.CategoryEntity;
import com.example.project.entity.ProductEntity;
import com.example.project.repository.CategoryRepository;
import com.example.project.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DataInitTest {
    @Autowired
    private CategoryRepository categortyRepository;

    @Autowired
    private ProductRepository productRepository;

    @Rollback(value = false)
    @Test
    void create_category(){
        CategoryEntity category = CategoryEntity.builder()
                .name("Men")
                .description("Sản phẩm thời trang dành cho nam giới")
                .build();
        CategoryEntity category1 = CategoryEntity.builder()
                .name("Women")
                .description("Sản phẩm thời trang dành cho nữ giới")
                .build();
        CategoryEntity category2 = CategoryEntity.builder()
                .name("Featured Products")
                .description("Sản phẩm thời trang nổi bật")
                .build();
        CategoryEntity category3 = CategoryEntity.builder()
                .name("New Products")
                .description("Sản phẩm thời trang mới")
                .build();
        categortyRepository.saveAll(List.of(category, category1, category2, category3));
    }

    @Test
    @Rollback(value = false)
    void update_category(){
        categortyRepository.updateCategory(5L, "Update", "Update test");
    }

    @Test
    @Rollback(value = false)
    void create_product(){
        ProductEntity product = ProductEntity.builder()
                .name("Áo T-Shirt nữ cổ vuông dài tay")
                .description("Áo T-Shirt nữ cổ vuông dài tay")
                .content("- Áo T-shirt chất liệu cotton nhẹ, co dãn 4 chiều.\n" +
                        "- Thiết kế phom basic dễ mặc, thoải mái trong mọi hoạt động.\n" +
                        "- Kiểu dáng và màu sắc trẻ trung, hiện đại phù hợp với cô nàng năng động.")
                .build();
        productRepository.save(product);
    }
}
