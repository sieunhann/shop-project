package com.example.project.entity.product;

import com.example.project.dto.VariantDto;
import com.example.project.entity.BaseEntity;
import com.example.project.entity.product.ProductEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@NamedNativeQuery(
        name = "getVariantDtos",
        query = "SELECT v.id, p.id AS pId, p.name AS pName, v.sku, v.color, v.size, v.price, v.quantity, v.created_at, v.updated_at \n" +
                "FROM variant v LEFT JOIN product p ON v.product_id = p.id\n" +
                "WHERE (:query IS NULL OR p.name LIKE CONCAT('%', :query ,'%') OR v.sku LIKE CONCAT('%', :query ,'%')) \n" +
                "ORDER BY p.name",
        resultSetMapping = "Variants"
)
@NamedNativeQuery(
        name = "getVariantDtos.count",
        query = "SELECT COUNT(v.id) AS count FROM variant v LEFT JOIN product p ON v.product_id = p.id\n" +
                "WHERE (:query IS NULL OR p.name LIKE CONCAT('%', :query ,'%') OR v.sku LIKE CONCAT('%', :query ,'%')) \n",
        resultSetMapping = "countVariants"
)
@SqlResultSetMapping(
        name = "Variants",
        classes = @ConstructorResult(
                targetClass = VariantDto.class,
                columns = {
                        @ColumnResult(name = "id", type = Long.class),
                        @ColumnResult(name = "pId", type = Long.class),
                        @ColumnResult(name = "pName", type = String.class),
                        @ColumnResult(name ="sku", type = String.class),
                        @ColumnResult(name ="color", type = String.class),
                        @ColumnResult(name = "size", type = String.class),
                        @ColumnResult(name = "price", type = Double.class),
                        @ColumnResult(name ="quantity", type = Integer.class),
                        @ColumnResult(name ="created_at", type = LocalDateTime.class),
                        @ColumnResult(name ="updated_at", type = LocalDateTime.class)
                }
        )
)
@SqlResultSetMapping(
        name = "countVariants",
        classes = @ConstructorResult(
                targetClass = java.lang.Long.class,
                columns = {
                        @ColumnResult(name = "count", type = Long.class)
                }
        )
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "variant")
public class VariantEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "sku", nullable = false, unique = true)
    private String sku;

    @Column(name = "size")
    private String size;

    @Column(name = "color")
    private String color;

    @Column(name = "price")
    @Min(value = 0)
    private Double price;

    @Column(name = "quantity")
    @Min(value = 0)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private ProductEntity productEntity;

    @PrePersist
    void prePersist(){
        this.setCreatedAt(LocalDateTime.now());
        this.setUpdatedAt(LocalDateTime.now());
        this.quantity = 0;
    }
}