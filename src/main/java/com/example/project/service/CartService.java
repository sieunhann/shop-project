package com.example.project.service;

import com.example.project.entity.CartEntity;
import com.example.project.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    public CartEntity getById(Long id){
        return cartRepository.findById(id).get();
    }

    public CartEntity createCart(CartEntity cartRequest) {
        CartEntity cart = CartEntity.builder()
                .note(cartRequest.getNote())
                .build();
        cartRepository.save(cart);
        return cart;
    }
}
