package com.example.project.dto;

import com.example.project.entity.product.ProductEntity;
import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class WebProductDto {
    private Page<ProductEntity> productPage;
    private int currentPage;
    private List<Integer> pageNumbers;
}
