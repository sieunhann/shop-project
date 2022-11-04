package com.example.project.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "cart")
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;

    @OneToMany(mappedBy = "cartEntity", orphanRemoval = true)
    private List<CartItemEntity> cartItemEntities = new ArrayList<>();

}