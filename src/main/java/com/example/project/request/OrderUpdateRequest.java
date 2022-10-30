package com.example.project.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderUpdateRequest {
    private String note;
    private String status;
    private String payment;
    private String fulfillment;
}
