package com.example.project.controller.admin.order;

import com.example.project.entity.order.ShippingAddressEntity;
import com.example.project.service.order.ShippingAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ShippingAddressController {
    @Autowired
    private ShippingAddressService shippingAddressService;

    // Lấy thông tin nhận hàng của order
    @GetMapping("/api/v1/orders/{id}/shipping")
    public ResponseEntity<?> getByOrder(@PathVariable String id){
        return ResponseEntity.ok(shippingAddressService.getByOrder(id));
    }

    // Cập nhật thông tin nhận hàng của order
    @PutMapping("/api/v1/orders/{id}/shipping")
    public ResponseEntity<?> updateByOrder(@PathVariable String id,
                                           @RequestBody ShippingAddressEntity request){
        return ResponseEntity.ok(shippingAddressService.updateByOrder(id, request));
    }


}
