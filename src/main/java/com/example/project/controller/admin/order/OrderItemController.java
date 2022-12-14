package com.example.project.controller.admin.order;

import com.example.project.service.order.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderItemController {
    @Autowired
    private OrderItemService orderItemService;

    @GetMapping("/api/v1/orders/{id}/order-items")
    public ResponseEntity<?> getByOrder(@PathVariable String id){
        return ResponseEntity.ok(orderItemService.getByOrder(id));
    }
}
