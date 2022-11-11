package com.example.project.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class WebController {

    @GetMapping("/shop")
    public String getHomePage(){
        return "web/index";
    }

    // Truy cập trang nhóm sp
    @GetMapping("/shop/products")
    public String getProductsPage(){
        return "web/shop";
    }

    // Truy cập trang chi tiết sp
    @GetMapping("/shop/products/{id}")
    public String getProductPage(@PathVariable(value = "id") Long id){
        return "web/product-details";
    }

    // Truy cập trang giỏ hàng
    @GetMapping("/shop/cart")
    public String getCartPage(){
        return "web/shop-cart";
    }

    // Truy cập trang checkout
    @GetMapping("/shop/checkout")
    public String getCheckOutPage(){
        return "web/checkout";
    }

    @GetMapping("/shop/detail")
    public String getDetailPage(){
        return "web/customer-detail";
    }

    @GetMapping("/shop/contact")
    public String getContactPage(){
        return "web/contact";
    }
}
