package com.example.project.service.order;

import com.example.project.entity.order.OrderEntity;
import com.example.project.entity.order.OrderItemEntity;
import com.example.project.exception.NotFoundException;
import com.example.project.repository.order.OrderRepository;
import com.example.project.repository.product.ProductRepository;
import com.example.project.repository.product.VariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private VariantRepository variantRepository;

    // Lấy order item theo order
    public List<OrderItemEntity> getByOrder(String id) {
        OrderEntity order = orderRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Đơn hàng không tồn tại");
        });
        return order.getOrderItemEntities();
    }

}
