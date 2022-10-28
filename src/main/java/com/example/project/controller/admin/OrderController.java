package com.example.project.controller.admin;

import com.example.project.request.OrderCreateRequest;
import com.example.project.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/admin/orders")
    public String getOrdersPage(){
        return "/admin/orders/admin-orders";
    }

    @GetMapping("/admin/order/create")
    public String getOrderCreatePage(){
        return "/admin/orders/admin-order-create";
    }

    @PostMapping(value = "/api/v1/admin/order")
    public ResponseEntity<?> createOrder(@RequestBody OrderCreateRequest request){
        return ResponseEntity.ok(orderService.createOrder(request));
    }
}
