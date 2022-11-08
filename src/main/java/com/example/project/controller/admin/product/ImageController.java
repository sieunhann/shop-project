package com.example.project.controller.admin.product;

import com.example.project.service.product.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ImageController {
    @Autowired
    private ImageService imageService;

    @PostMapping(value = "/api/v1/products/{id}/images", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> uploadImage(@PathVariable Long id,
                                         @RequestPart("file[]") MultipartFile[] files){
        return ResponseEntity.ok(imageService.uploadImageForProduct(id, files));
    }

    // Xóa ảnh
    @DeleteMapping("/api/v1/images/{id}")
    public ResponseEntity<?> deleteImage(@PathVariable Long id){
        imageService.deleteFile(id);
        return ResponseEntity.noContent().build();
    }
}
