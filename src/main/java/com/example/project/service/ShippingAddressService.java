package com.example.project.service;

import com.example.project.entity.OrderEntity;
import com.example.project.entity.ShippingAddressEntity;
import com.example.project.exception.NotFoundException;
import com.example.project.repository.OrderRepository;
import com.example.project.repository.ShippingAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShippingAddressService {
    @Autowired
    private ShippingAddressRepository shippingAddressRepository;
    @Autowired
    private OrderRepository orderRepository;

    // Lấy thông tin nhận hàng của order
    public ShippingAddressEntity getByOrder(String id) {
        OrderEntity order = orderRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Đơn hàng không tồn tại");
        });
        return shippingAddressRepository.findByOrderEntity(order);
    }

    // Cập nhật thông tin nhận hàng của order
    public ShippingAddressEntity updateByOrder(String id, ShippingAddressEntity request) {
        OrderEntity order = orderRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Đơn hàng không tồn tại");
        });
        ShippingAddressEntity shippingAddress = order.getShippingAddress();
        shippingAddress.setName(request.getName());
        shippingAddress.setPhone(request.getPhone());
        shippingAddress.setEmail(request.getEmail());
        shippingAddress.setAddress(request.getAddress());
        shippingAddress.setCity(request.getCity());
        shippingAddressRepository.save(shippingAddress);
        return shippingAddress;
    }
}
