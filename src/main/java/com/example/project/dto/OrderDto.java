package com.example.project.dto;

import com.example.project.entity.OrderFulfillment;
import com.example.project.entity.OrderStatus;
import com.example.project.entity.OrderPayment;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrderDto {
    private String id;
    private Double total;
    private OrderStatus status;
    private OrderPayment payment;
    private OrderFulfillment fulfillment;
    private LocalDateTime createdAt;
    private String customerName;

    public OrderDto(String id, Double total, String status, String payment,
                    String fulfillment, LocalDateTime createdAt, String customerName) {
        this.id = id;
        this.total = total;
        this.status = OrderStatus.valueOf(status);
        this.payment = OrderPayment.valueOf(payment);
        this.fulfillment = OrderFulfillment.valueOf(fulfillment);
        this.createdAt = createdAt;
        this.customerName = customerName;
    }
}
