package com.example.project.controller.admin;

import com.example.project.entity.CategoryEntity;
import com.example.project.request.CategoryRequest;
import com.example.project.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/admin/categories")
    public String getCategoriesPage(Model model,
                                   @RequestParam(name = "name", required = false) String name,
                                   @RequestParam(name = "page", defaultValue = "1") int currentPage,
                                   @RequestParam(name = "limit", defaultValue = "10") int pageSize){

        Page<CategoryEntity> categoryPage
                = categoryService.getCategories(name, PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("categoryPage", categoryPage);
        model.addAttribute("currentPage", currentPage);
        int totalPages = categoryPage.getTotalPages();
        if(totalPages > 0){
            model.addAttribute("pageNumbers", categoryService.getPageNumbers(totalPages));
        }

        return "admin/products/admin-categories";
    }

    @PostMapping("api/v1/category")
    @ResponseBody
    public void createCategory(@RequestBody CategoryRequest request){
        categoryService.createCategory(request);
    }

    @PutMapping( "api/v1/category/{id}")
    @ResponseBody
    public void updateCategory(@PathVariable("id") Long id,
                               @RequestBody CategoryRequest request){
        System.out.println(request.getName() + " - " + request.getDescription());
        categoryService.updateCategory(id, request);
    }

    @DeleteMapping ("api/v1/category/{id}")
    @ResponseBody
    public void deleteCategory(@PathVariable("id") Long id){
        categoryService.deleteCategory(id);
    }

    @GetMapping("/api/v1/categories")
    @ResponseBody
    public ResponseEntity<?> getCategories(){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }
}
