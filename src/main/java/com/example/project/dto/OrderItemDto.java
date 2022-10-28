package com.example.project.dto;

import lombok.*;

import javax.persistence.Column;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrderItemDto {
    private Long productId;
    private Long variantId;
    private Double price;
    private Integer quantity;
    private Double total;
}
