package com.example.project.controller.admin.product;

import com.example.project.entity.ProductEntity;
import com.example.project.request.ProductUpdateRequest;
import com.example.project.service.CategoryService;
import com.example.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Controller
public class ProductController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    // Truy cập trang tất cả sp
    @GetMapping("/admin/products")
    public String getProductsPage(Model model,
                                  @RequestParam(value = "query", required = false) String query,
                                  @RequestParam(value = "page", defaultValue = "1") int currentPage,
                                  @RequestParam(value = "limit", defaultValue = "4") int size){
        Page<ProductEntity> productPage =
                productService.getProducts(query, PageRequest.of(currentPage - 1, size));
        model.addAttribute("productPage", productPage);
        model.addAttribute("currentPage", currentPage);
        int totalPages = productPage.getTotalPages();
        if(totalPages > 0){
            model.addAttribute("pageNumbers", productService.getPageNumbers(totalPages));
        }
        return "admin/products/admin-products";
    }

    // Truy cập trang tạo sp
    @GetMapping("/admin/products/create")
    public String getProductCreatePage(Model model){
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin/products/admin-product-create";
    }

    // Tạo sản phẩm mới
    @PostMapping(value = "/api/v1/product",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,
                    MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> createProduct(@RequestPart("file[]") MultipartFile[] files,
                                           @RequestPart("applicant") String applicant){

        return ResponseEntity.ok(productService.createProduct(files, applicant));
    }


    // Truy cập trang chi tiết sp
    @GetMapping("/admin/product/{id}")
    public String getProductPage(@PathVariable("id") Long id){
        return "/admin/products/admin-product";
    }

    // Lấy thông tin sp theo id
    @GetMapping("/api/v1/product/{id}")
    public ResponseEntity<?> getProduct(@PathVariable("id") Long id){
        return ResponseEntity.ok(productService.getProductById(id));
    }

    // Cập nhật thông tin sp
    @PutMapping("/api/v1/product/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") Long id,
                                           @RequestBody ProductUpdateRequest request){
        return ResponseEntity.ok(productService.updateProduct(id, request));
    }

    @DeleteMapping("/api/v1/product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Xem file
    // Hoac co the su dung
    // @GetMapping(value = "/product/{id}/images/{fileId}", produces = {MediaType.IMAGE_JPEG})
    @GetMapping("/api/v1/product/images/{fileId}")
    public ResponseEntity<?> readImage(@PathVariable String fileId){
        byte[] bytes = productService.readFile(fileId);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(bytes);
    }


}
