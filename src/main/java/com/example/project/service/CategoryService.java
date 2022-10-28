package com.example.project.service;

import com.example.project.entity.CategoryEntity;
import com.example.project.exception.BadRequestException;
import com.example.project.repository.CategoryRepository;
import com.example.project.request.CategoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    // Lấy danh sách toàn bộ nhóm sp
    public List<CategoryEntity> getAllCategories(){
        return categoryRepository.findAll();
    }

    public CategoryEntity getById(Long id){
        return categoryRepository.findById(id).get();
    }

    // Lấy danh sách các trang
    public List<Integer> getPageNumbers(int totalPages){
        return IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
    }

    // Lấy danh sách nhóm sp và phân trang
    public Page<CategoryEntity> getCategories(String name, Pageable pageable){
        return categoryRepository.findCategories(name, pageable);
    }

    public void updateCategory(Long id, CategoryRequest request){
        categoryRepository.updateCategory(id, request.getName(), request.getDescription());
    }

    public void deleteCategory(Long id){
        CategoryEntity entity = categoryRepository.findById(id).get();
        categoryRepository.delete(entity);
    }

    public void createCategory(CategoryRequest request){
        if(categoryRepository.findByNameContainingIgnoreCase(request.getName()).isPresent()){
            throw new BadRequestException("Nhóm sản phẩm đã tồn tại");
        }
        CategoryEntity category = CategoryEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
        categoryRepository.save(category);
    }
}
