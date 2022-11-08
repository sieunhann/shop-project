package com.example.project.dto;

import com.example.project.entity.product.VariantEntity;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CartItemDto {
    private Long itemId;
    private String imageUrl;
    private Long productId;
    private String productName;
    private Integer quantity;
    private VariantEntity variant;
    private Double total;
}
