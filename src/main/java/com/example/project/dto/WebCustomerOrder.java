package com.example.project.dto;

import com.example.project.entity.OrderEntity;
import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class WebCustomerOrder {
    private Page<OrderEntity> orderPage;
    private int currentPage;
    private List<Integer> pageNumbers;
}
