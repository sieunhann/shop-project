package com.example.project.controller.web;

import com.example.project.entity.order.CartEntity;
import com.example.project.request.CartRequest;
import com.example.project.service.order.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebCartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/api/v1/shop/cart/{id}")
    public ResponseEntity<?> getCart(@PathVariable Long id){
        return ResponseEntity.ok(cartService.getCartDto(id));
    }

    @PostMapping("/api/v1/shop/cart")
    public ResponseEntity<?> addToCart(@RequestBody CartRequest request){
        return ResponseEntity.ok(cartService.addToCart(request));
    }

    @PutMapping("/api/v1/shop/cart/{id}")
    public ResponseEntity<?> updateCart(@PathVariable Long id, @RequestBody CartEntity request){
        return ResponseEntity.ok(cartService.updateCart(id, request));
    }
}
