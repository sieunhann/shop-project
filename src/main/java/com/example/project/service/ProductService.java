package com.example.project.service;

import com.example.project.entity.CategoryEntity;
import com.example.project.entity.ImageEntity;
import com.example.project.entity.ProductEntity;
import com.example.project.exception.NotFoundException;
import com.example.project.repository.ImageRepository;
import com.example.project.repository.ProductRepository;
import com.example.project.repository.VariantRepository;
import com.example.project.request.ProductCreateRequest;
import com.example.project.request.ProductUpdateRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private VariantRepository variantRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ImageService imageService;


    // Lấy danh sách sản phẩm
    public Page<ProductEntity> getProducts(String query, Pageable pageable){
        return productRepository.findByName(query, pageable);
    }

    // Lấy danh sách các trang
    public List<Integer> getPageNumbers(int totalPages){
        return IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
    }

    // Tạo sản phẩm mới
    public ProductEntity createProduct(MultipartFile[] files, String applicant){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ProductCreateRequest request = objectMapper.readValue(applicant, ProductCreateRequest.class);
            Set<CategoryEntity> categories = new LinkedHashSet<>();
            request.getCategoryId().forEach(ids -> categories.add(categoryService.getById(ids)));

            ProductEntity product = ProductEntity.builder()
                    .name(request.getName())
                    .content(request.getContent())
                    .description(request.getDescription())
                    .categoryEntities(categories).build();
            productRepository.save(product);

            if(request.getVariants().size() > 0) {
                request.getVariants().forEach(variant -> {
                    variant.setProductEntity(product);
                    variantRepository.save(variant);
                });
            }

            saveMultipleImage(files, product);

            return product;
        } catch (RuntimeException | JsonProcessingException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    // Cập nhật sản phẩm
    public ProductEntity updateProduct(Long id, ProductUpdateRequest request){
        ProductEntity product = getProductById(id);
        product.setName(request.getName());
        product.setContent(request.getContent());
        product.setDescription(request.getDescription());

        if(request.getCategoryId().size() > 0){
            Set<CategoryEntity> categories = new HashSet<>();
            request.getCategoryId().forEach(ids -> categories.add(categoryService.getById(ids)));
            product.setCategoryEntities(categories);
        } else {
            product.getCategoryEntities().clear();
        }
        productRepository.save(product);
        return product;
    }

    // Upload nhiều ảnh
    public void saveMultipleImage(MultipartFile[] files, ProductEntity product){
        if(files.length > 0) {
            Arrays.stream(files).toList().forEach(img -> {

                String url = imageService.uploadFile(img).replace("/api/v1/product/images/", "");
                ImageEntity image = ImageEntity.builder()
                        .url(url)
                        .productEntity(product)
                        .build();
                imageService.saveToDB(image);
            });
        }
    }

    // Xem image
    public byte[] readFile(String fileId) {
        return imageService.readFile(fileId);
    }

    // Lay danh sach image theo Id sản phẩm
    public List<String> getFilesByProductId(Long id) {
        ProductEntity product = productRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Can not find user");
        });
        return imageService.getFilesByProductId(id);
    }

    // Tìm kiếm sản phẩm theo id
    public ProductEntity getProductById(Long id){
        return productRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Không tồn tại sản phẩm với id = " + id);
        });
    }

    // Xóa sản phẩm
    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }


    // ======================================== SHOP ======================================== //

    // Lấy danh sách sản phẩm theo nhóm sp
    public List<ProductEntity> getNewProducts(Long cateId, int limit){
        return productRepository.findNewProductsByCategory(cateId, limit);
    }

    // Lấy danh sách sp và phân trang
    public Page<ProductEntity> getProductsPage(Long cateId, String color, String size, Double minPrice, Double maxPrice, Pageable pageable){
        return productRepository.findByCategoryPage(cateId, color, size, minPrice, maxPrice, pageable);
    }
}
