package com.example.project.request;

import lombok.*;

import java.util.List;

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
