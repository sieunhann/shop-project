package com.example.project.controller.web;

import com.example.project.request.OrderCreateRequest;
import com.example.project.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class WebOrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/api/v1/shop/checkout")
    public ResponseEntity<?> createOrder(HttpServletRequest request, @RequestBody OrderCreateRequest orderRequest){
        return ResponseEntity.ok(orderService.createOrderWeb(request, orderRequest));
    }
}
