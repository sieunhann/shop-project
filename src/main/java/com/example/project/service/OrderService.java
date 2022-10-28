package com.example.project.service;

import com.example.project.entity.*;
import com.example.project.repository.AccountRepository;
import com.example.project.repository.OrderItemRepository;
import com.example.project.repository.OrderRepository;
import com.example.project.repository.ShippingAddressRepository;
import com.example.project.request.OrderCreateRequest;
import com.example.project.security.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ShippingAddressRepository shippingAddressRepository;

    public OrderEntity createOrder(OrderCreateRequest request){
        OrderEntity order = OrderEntity.builder()
                .note(request.getNote())
                .total(request.getTotal())
                .accountEntity(request.getCustomer())
                .build();
        orderRepository.save(order);

        // Tạo thông tin nhận hàng
        ShippingAddressEntity shippingAddress = request.getShippingAddress();
        shippingAddress.setOrderEntity(order);
        shippingAddressRepository.save(shippingAddress);

        // Tạo order items
        List<OrderItemEntity> items = request.getOrderItems();
        items.forEach(item -> {
            item.setOrderEntity(order);
            orderItemRepository.save(item);
        });


        return order;
    }

    public void setCustomer(HttpServletRequest request, OrderCreateRequest orderRequest){
        // Lấy token từ trong header của request
        String token = jwtUtils.getTokenFromCookie(request);

        if(token != null){
            // Parse thông tin từ token
            Claims claims = jwtUtils.getClaimsFromToken(token);

            // Lấy username (email khách hàng)
            String userName = claims.getSubject();

            // Lấy thông tin khách hàng qua email
            AccountEntity customer = accountRepository.findByEmail(userName).get();

            orderRequest.setCustomer(customer);
        } else {
            orderRequest.setCustomer(null);
        }
    }
}
