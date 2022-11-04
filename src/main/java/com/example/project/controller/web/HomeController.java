package com.example.project.controller.web;

import com.example.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @Autowired
    private ProductService productService;


    @GetMapping("/api/v1/shop/products/new")
    public ResponseEntity<?> getNewProducts(@RequestParam(name = "query", defaultValue = "4") Long query,
                                            @RequestParam(name = "limit", defaultValue = "8") int limit){
        return ResponseEntity.ok(productService.getNewProducts(query, limit));
    }
}
