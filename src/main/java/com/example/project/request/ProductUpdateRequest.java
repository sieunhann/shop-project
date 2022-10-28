package com.example.project.request;

import com.example.project.entity.ImageEntity;
import com.example.project.entity.VariantEntity;
import lombok.*;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductUpdateRequest {
    private String name;
    private String content;
    private String description;
    private List<Long> categoryId;
}
