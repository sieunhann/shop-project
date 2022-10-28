package com.example.project.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class VariantRequest {
    private String sku;
    private String color;
    private String size;
    private Double price;
    private Integer quantity;
}
