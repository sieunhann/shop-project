package com.example.project.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CartDto {
    private String note;
    private List<CartItemDto> items;
}
