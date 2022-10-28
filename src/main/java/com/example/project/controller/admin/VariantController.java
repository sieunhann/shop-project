package com.example.project.controller.admin;

import com.example.project.dto.VariantDto;
import com.example.project.entity.ProductEntity;
import com.example.project.request.VariantRequest;
import com.example.project.service.VariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class VariantController {
    @Autowired
    private VariantService variantService;

    @GetMapping("/admin/variants")
    public String getVariantsPage(Model model,
                                  @RequestParam(value = "query", required = false) String query,
                                  @RequestParam(value = "page", defaultValue = "1") int currentPage,
                                  @RequestParam(value = "limit", defaultValue = "10") int size){
        Page<VariantDto> variantPage =
                variantService.getVariantDtosPage(query, PageRequest.of(currentPage - 1, size));
        model.addAttribute("variantPage", variantPage);
        model.addAttribute("currentPage", currentPage);
        int totalPages = variantPage.getTotalPages();
        if(totalPages > 0){
            model.addAttribute("pageNumbers", variantService.getPageNumbers(totalPages));
        }
        return "/admin/products/admin-variants";
    }

    @GetMapping("/api/v1/order/variant")
    public ResponseEntity<?> getVariantsList(@RequestParam(value = "query", required = false) String query){
        return ResponseEntity.ok(variantService.getVariantDtos(query));
    }

    // Lấy tất cả phiên bản của sp
    @GetMapping("/api/v1/product/variants/{id}")
    public ResponseEntity<?> getVariantsByProductId(@PathVariable Long id){
        return ResponseEntity.ok(variantService.getByProduct(id));
    }

    // Tạo phiên bản sp mới
    @PostMapping("/api/v1/product/variant/{id}")
    public ResponseEntity<?> createVariant(@PathVariable Long id, @RequestBody VariantRequest request){
        return ResponseEntity.ok(variantService.createVariant(id, request));
    }

    // Xóa phiên bản sp
    @DeleteMapping("/api/v1/variant/{id}")
    public ResponseEntity<?> deleteVariant(@PathVariable Long id){
        variantService.deleteVariant(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/api/v1/variant/{id}")
    public ResponseEntity<?> updateVariant(@PathVariable Long id,
                                           @RequestBody VariantRequest request){
        return ResponseEntity.ok(variantService.updateVariant(id, request));
    }
}
