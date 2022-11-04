package com.example.project.dto;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Builder
public class VariantDto {
    private Long varId;
    private Long proId;
    private String proName;
    private String varSku;
    private String varColor;
    private String varSize;
    private Double varPrice;
    private Integer varQuantity;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public VariantDto(Long varId, Long proId, String proName, String varSku, String varColor, String varSize, Double varPrice,
                      Integer varQuantity, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.varId = varId;
        this.proId = proId;
        this.proName = proName;
        this.varSku = varSku;
        this.varColor = varColor;
        this.varSize = varSize;
        this.varPrice = varPrice;
        this.varQuantity = varQuantity;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
