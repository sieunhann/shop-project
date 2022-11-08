package com.example.project.request;

import com.example.project.entity.account.AccountEntity;
import com.example.project.entity.order.OrderItemEntity;
import com.example.project.entity.order.ShippingAddressEntity;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderCreateRequest {
    private String note;
    private Double total;
    private List<OrderItemEntity> orderItems;
    private ShippingAddressEntity shippingAddress;
    private AccountEntity customer;
}
