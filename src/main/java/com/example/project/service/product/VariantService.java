package com.example.project.service.product;

import com.example.project.dto.VariantDto;
import com.example.project.entity.product.ProductEntity;
import com.example.project.entity.product.VariantEntity;
import com.example.project.exception.NotFoundException;
import com.example.project.repository.product.ProductRepository;
import com.example.project.repository.product.VariantRepository;
import com.example.project.request.VariantRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class VariantService {
    @Autowired
    private VariantRepository variantRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<VariantDto> getVariantDtos(String query){
        return variantRepository.getVariantDtos(query);
    }

    public Page<VariantDto> getVariantDtosPage(String query, Pageable pageable){
        return variantRepository.getVariantDtosPage(query, pageable);
    }
    // Lấy danh sách các trang
    public List<Integer> getPageNumbers(int totalPages){
        return IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
    }

    // Tìm phiên bản theo id sp
    public List<VariantEntity> getByProduct(Long ids){
        ProductEntity product = productRepository.findById(ids).orElseThrow(() -> {
            throw new NotFoundException("Không tồn tại sản phẩm có id = " + ids);
        });
        return variantRepository.findByProductEntity(product);
    }

    // Tạo phiên bản mới cho sp
    public VariantEntity createVariant(Long ids, VariantRequest request){
        VariantEntity variant = VariantEntity.builder()
                .sku(request.getSku())
                .color(request.getColor())
                .size(request.getSize())
                .price(request.getPrice())
                .productEntity(productRepository.findById(ids).get())
                .build();
        variantRepository.save(variant);
        return variant;
    }

    // Xóa phiên bản
    public void deleteVariant(Long ids){
        variantRepository.deleteById(ids);
    }

    // Update phiên bản sp
    public VariantEntity updateVariant(Long ids, VariantRequest request){
        VariantEntity variant = variantRepository.findById(ids).orElseThrow(() -> {
            throw new NotFoundException("Không tồn tại phiên bản có id = " + ids);
        });
        variant.setPrice(request.getPrice());
        variant.setQuantity(request.getQuantity());
        variantRepository.save(variant);
        return variant;
    }

    // Lấy các loại màu sắc
    public List<String> getColor(){
        return variantRepository.getVariantColor();
    }

    // Lấy các loại kích thước
    public List<String> getSize(){
        return variantRepository.getVariantSize();
    }
}
