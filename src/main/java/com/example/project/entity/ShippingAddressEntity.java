package com.example.project.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "shipping_address")
public class ShippingAddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email")
    @Pattern(regexp = "^[a-zA-Z][\\w]+@([\\w]+\\.[\\w]{2,}|[\\w]+\\.[\\w]{2,}\\.[\\w]{2,})$")
    private String email;

    @Column(name = "phone", nullable = false)
    @Pattern(regexp = "^(0|\\+84)[1-9][0-9]{8}$")
    private String phone;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "city", nullable = false)
    private String city;

    @OneToOne
    @JoinColumn(name = "order_id")
    private OrderEntity orderEntity;

}
