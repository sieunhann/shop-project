package com.example.project.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
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
@Table(name = "orders")
public class OrderEntity extends BaseEntity {
    @Id
    @GeneratedValue(generator = "prod-generator")
    @GenericGenerator(name = "prod-generator",

            parameters = @org.hibernate.annotations.Parameter(name = "prefix", value = "#DH"),

            strategy = "com.example.project.config.CustomGenerateId")
    @Column(name = "id")
    private String id;

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;

    @Column(name = "total")
    private Double total;

    @Column(name = "status")
    private EnumOrderStatus status;

    @Column(name = "payment")
    private EnumPayment payment;

    @Column(name = "fulfillment")
    private EnumFulfillment fulfillment;


    @OneToMany(mappedBy = "orderEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderItemEntity> orderItemEntities = new LinkedHashSet<>();

    @OneToMany(mappedBy = "orderEntity", orphanRemoval = true)
    private List<RefundEntity> refundEntities = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "account_id")
    private AccountEntity accountEntity;

    @OneToOne(mappedBy = "orderEntity", orphanRemoval = true)
    private ShippingAddressEntity shippingAddress;

    @PrePersist
    public void prePersist(){
        this.status = EnumOrderStatus.NEW;
        this.payment = EnumPayment.UNPAID;
        this.fulfillment = EnumFulfillment.UNFULFILLED;
    }
}
