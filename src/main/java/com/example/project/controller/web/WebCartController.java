package com.example.project.controller.web;

import com.example.project.entity.CartEntity;
import com.example.project.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class WebCartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/shop/cart")
    public String getCartPage(){
        return "/web/shop-cart";
    }

    @GetMapping("/api/v1/shop/cart/{id}")
    public ResponseEntity<?> getCart(@PathVariable Long id){
        return ResponseEntity.ok(cartService.getById(id));
    }

    @PostMapping("/api/v1/shop/cart")
    public ResponseEntity<?> createCart(@RequestBody CartEntity cartRequest){
        return ResponseEntity.ok(cartService.createCart(cartRequest));
    }
}
