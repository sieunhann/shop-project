package com.example.project.controller.web;

import com.example.project.entity.order.CartItemEntity;
import com.example.project.service.order.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class WebCartItemController {

    @Autowired
    private CartItemService cartItemService;

    @DeleteMapping("/api/v1/shop/cart/items/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable Long id){
        cartItemService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/api/v1/shop/cart/items/{id}")
    public ResponseEntity<?> updateItem(@PathVariable Long id,
                                        @RequestBody CartItemEntity request){
        return ResponseEntity.ok(cartItemService.updateItem(id, request));
    }

}
