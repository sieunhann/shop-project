package com.example.project.request;

import com.example.project.entity.product.VariantEntity;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductCreateRequest {
    private String name;
    private String content;
    private String description;
    private List<Long> categoryId;
    private List<VariantEntity> variants;
}
