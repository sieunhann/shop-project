package com.example.project.service.order;

import com.example.project.dto.CartDto;
import com.example.project.dto.CartItemDto;
import com.example.project.entity.order.CartEntity;
import com.example.project.entity.order.CartItemEntity;
import com.example.project.entity.product.ProductEntity;
import com.example.project.entity.product.VariantEntity;
import com.example.project.exception.BadRequestException;
import com.example.project.exception.NotFoundException;
import com.example.project.repository.order.CartItemRepository;
import com.example.project.repository.order.CartRepository;
import com.example.project.repository.product.VariantRepository;
import com.example.project.request.CartRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private VariantRepository variantRepository;

    public CartEntity getById(Long id) {
        return cartRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Giỏ hàng không tồn tại");
        });
    }

    public CartDto getCartDto(Long id) {
        if(id == 0){
            return new CartDto();
        }
        else {
            CartEntity cart = getById(id);
            List<CartItemEntity> items = cart.getCartItemEntities();
            List<CartItemDto> itemDtos = new ArrayList<>();

            items.forEach(item -> {
                if (getCartItemDto(item) != null) {
                    itemDtos.add(getCartItemDto(item));
                }
            });

            return CartDto.builder()
                    .note(cart.getNote())
                    .items(itemDtos)
                    .build();
        }
    }

    // Lấy CartItemDto theo CartItemEntity
    public CartItemDto getCartItemDto(CartItemEntity cartItem) {
        Optional<VariantEntity> variantOp = variantRepository.findById(cartItem.getVariantId());
        if (variantOp.isEmpty()) {
            cartItemRepository.delete(cartItem);
            return null;
        } else {
            VariantEntity variant = variantOp.get();
            ProductEntity product = variant.getProductEntity();
            String url = product.getImageEntities().get(0).getUrl();

            Double total = cartItem.getQuantity() * variant.getPrice();

            CartItemDto item = CartItemDto.builder()
                    .itemId(cartItem.getId())
                    .imageUrl(url)
                    .productId(product.getId())
                    .productName(product.getName())
                    .quantity(cartItem.getQuantity())
                    .variant(variant)
                    .total(total)
                    .build();
            return item;
        }
    }

    @Transactional
    public CartRequest addToCart(CartRequest request) {
        CartItemEntity cartItem = CartItemEntity.builder()
                .variantId(request.getVariantId())
                .quantity(request.getQuantity())
                .build();

        if (request.getCartId() == 0) {
            CartEntity cart = new CartEntity();
            cartRepository.save(cart);

            cartItem.setCartEntity(cart);
            cartItemRepository.save(cartItem);

            request.setCartId(cart.getId());
        } else {
            CartEntity cart = cartRepository.findById(request.getCartId()).orElseThrow(() -> {
                throw new NotFoundException("Giỏ hàng không tồn tại");
            });

            List<CartItemEntity> items = cart.getCartItemEntities();
            Optional<CartItemEntity> myItem = items.stream()
                    .filter(item -> item.getVariantId().equals(cartItem.getVariantId()))
                    .findFirst();
            if (myItem.isPresent()) {
                CartItemEntity oldItem = myItem.get();
                int newQty = (int) oldItem.getQuantity() + (int) cartItem.getQuantity();
                oldItem.setQuantity(newQty);
                cartItemRepository.save(oldItem);

            } else {
                cartItem.setCartEntity(cart);
                cartItemRepository.save(cartItem);
            }
        }
        return request;
    }

    @Transactional
    public CartEntity updateCart(Long id, CartEntity request) {
        CartEntity cart = getById(id);

        try{
            cartRepository.updateCart(id, request.getNote());
            return cart;
        } catch (Exception e){
            throw new BadRequestException("Cập nhật thất bại");
        }
    }

}
