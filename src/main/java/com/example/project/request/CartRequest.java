package com.example.project.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartRequest {
    private Long cartId;
    private String note;
    private Long variantId;
    private Integer quantity;
}
