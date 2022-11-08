package com.example.project.entity.order;

import com.example.project.entity.order.CartEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "cart_item")
public class CartItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "variant_id")
    private Long variantId;

    @Column(name = "quantity")
    private Integer quantity;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private CartEntity cartEntity;

}
