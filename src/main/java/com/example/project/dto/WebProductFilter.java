package com.example.project.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class WebProductFilter {
    private List<String> sizeList;
    private List<String> colorList;
}
