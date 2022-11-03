package com.example.project.controller.web;

import com.example.project.entity.CartItemEntity;
import com.example.project.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebCartItemController {

    @Autowired
    private CartItemService cartItemService;

    @PostMapping("/api/v1/shop/cart/item")
    public ResponseEntity<?> createCartItem(@RequestBody CartItemEntity request){
        return ResponseEntity.ok(cartItemService.createCartItem(request));
    }
}
