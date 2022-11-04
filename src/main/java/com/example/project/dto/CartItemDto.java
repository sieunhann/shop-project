package com.example.project.dto;

import com.example.project.entity.VariantEntity;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CartItemDto {
    private Long itemId;
    private String imageUrl;
    private String productName;
    private Integer quantity;
    private VariantEntity variant;
    private Double total;
}
