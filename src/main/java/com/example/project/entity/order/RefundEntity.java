package com.example.project.entity.order;

import com.example.project.entity.BaseEntity;
import com.example.project.entity.order.OrderEntity;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "refund")
public class RefundEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "order_item_id")
    private Long orderItemId;

    @Column(name = "total")
    private Double total;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity orderEntity;

}