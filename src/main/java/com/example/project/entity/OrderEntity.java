package com.example.project.entity;

import com.example.project.dto.OrderDto;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NamedNativeQuery(
        name = "getOrderDtos",
        query = "SELECT o.id, o.total, o.status, o.payment, o.fulfillment, o.created_at, a.name\n" +
                "FROM orders o LEFT JOIN account a ON o.account_id = a.id " +
                "WHERE (:query IS NULL OR o.id LIKE CONCAT('%', :query, '%')) " +
                "OR (:query IS NULL OR a.name LIKE CONCAT('%', :query, '%')) " +
                "ORDER BY o.created_at DESC",
        resultSetMapping = "OrderDtos"
)
@NamedNativeQuery(
        name = "getOrderDtos.count",
        query = "SELECT COUNT(o.id) AS count FROM orders o",
        resultSetMapping = "countOrderDtos"
)
@SqlResultSetMapping(
        name = "OrderDtos",
        classes = @ConstructorResult(
                targetClass = OrderDto.class,
                columns = {
                        @ColumnResult(name = "id", type = String.class),
                        @ColumnResult(name = "total", type = Double.class),
                        @ColumnResult(name = "status", type = String.class),
                        @ColumnResult(name ="payment", type = String.class),
                        @ColumnResult(name ="fulfillment", type = String.class),
                        @ColumnResult(name ="created_at", type = LocalDateTime.class),
                        @ColumnResult(name = "name", type = String.class)
                }
        )
)
@SqlResultSetMapping(
        name = "countOrderDtos",
        classes = @ConstructorResult(
                targetClass = java.lang.Long.class,
                columns = {
                        @ColumnResult(name = "count", type = Long.class)
                }
        )
)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity {
    @Id
    @GeneratedValue(generator = "prod-generator")
    @GenericGenerator(name = "prod-generator",

            parameters = @org.hibernate.annotations.Parameter(name = "prefix", value = "DH"),

            strategy = "com.example.project.config.CustomGenerateId")
    @Column(name = "id")
    private String id;

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;

    @Column(name = "total")
    private Double total;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "payment")
    @Enumerated(EnumType.STRING)
    private OrderPayment payment;

    @Column(name = "fulfillment")
    @Enumerated(EnumType.STRING)
    private OrderFulfillment fulfillment;


    @OneToMany(mappedBy = "orderEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemEntity> orderItemEntities = new ArrayList<>();

    @OneToMany(mappedBy = "orderEntity", orphanRemoval = true)
    private List<RefundEntity> refundEntities = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "account_id")
    private AccountEntity accountEntity;

    @OneToOne(mappedBy = "orderEntity", orphanRemoval = true)
    private ShippingAddressEntity shippingAddress;

    @PrePersist
    public void prePersist(){
        this.status = OrderStatus.NEW;
        this.payment = OrderPayment.UNPAID;
        this.fulfillment = OrderFulfillment.UNFULFILLED;
    }
}
