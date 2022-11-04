package com.example.project.controller.web;

import com.example.project.dto.WebProductDto;
import com.example.project.entity.ProductEntity;
import com.example.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WebProductController {
    @Autowired
    private ProductService productService;

    // Lấy danh sách sp
    @GetMapping("/api/v1/shop/products")
    public ResponseEntity<?> getProducts(@RequestParam(value = "category", required = false) Long cateId,
                                      @RequestParam(value = "color", required = false) String color,
                                      @RequestParam(value = "size", required = false) String size,
                                      @RequestParam(value = "min", required = false) Double minPrice,
                                      @RequestParam(value = "max", required = false) Double maxPrice,
                                      @RequestParam(value = "page", defaultValue = "1") int currentPage,
                                      @RequestParam(value = "limit", defaultValue = "9") int pageSize){
        Page<ProductEntity> productPage =
                productService.getProductsPage(cateId, color, size, minPrice, maxPrice, PageRequest.of(currentPage - 1, pageSize));
        int totalPages = productPage.getTotalPages();
        List<Integer> pageNumbers = new ArrayList<>();
        if(totalPages > 0){
            pageNumbers = productService.getPageNumbers(totalPages);
        }
        WebProductDto productDto = WebProductDto.builder().
                productPage(productPage)
                .currentPage(currentPage)
                .pageNumbers(pageNumbers).build();
        return ResponseEntity.ok(productDto);
    }

    // Lấy thông tin sản phẩm
    @GetMapping("/api/v1/shop/products/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id){
        return ResponseEntity.ok(productService.getProductById(id));
    }
}
