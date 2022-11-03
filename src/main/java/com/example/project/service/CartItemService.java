package com.example.project.service;

import com.example.project.entity.CartItemEntity;
import com.example.project.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;

    public CartItemEntity createCartItem(CartItemEntity request){
        CartItemEntity cartItem = CartItemEntity.builder()
                .quantity(request.getQuantity())
                .variantId(request.getVariantId())
                .cartEntity(request.getCartEntity()).build();
        cartItemRepository.save(cartItem);
        return cartItem;
    }
}
