package com.example.project.service;

import com.example.project.entity.CartItemEntity;
import com.example.project.exception.BadRequestException;
import com.example.project.exception.NotFoundException;
import com.example.project.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;

    @Transactional
    public void deleteById(Long id) {
        try {
            cartItemRepository.deleteById(id);
        } catch (Exception ex){
            throw new BadRequestException("Xóa thất bại");
        }
    }

    @Transactional
    public CartItemEntity updateItem(Long id, CartItemEntity request) {
        CartItemEntity item = cartItemRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Item không tồn tại");
        });
        item.setQuantity(request.getQuantity());
        cartItemRepository.save(item);
        return item;
    }

}
