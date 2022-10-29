package com.example.project.config;

import com.example.project.entity.OrderStatus;

import javax.persistence.AttributeConverter;
import java.util.stream.Stream;

public class OrderStatusConverter implements AttributeConverter<OrderStatus, String> {
    @Override
    public String convertToDatabaseColumn(OrderStatus orderStatus) {
        if (orderStatus == null) {
            return null;
        }
        return orderStatus.getCode();
    }

    @Override
    public OrderStatus convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }
        return Stream.of(OrderStatus.values())
                .filter(o -> o.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}

