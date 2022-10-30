package com.example.project.service;

import com.example.project.dto.OrderDto;
import com.example.project.entity.*;
import com.example.project.exception.NotFoundException;
import com.example.project.repository.*;
import com.example.project.request.OrderCreateRequest;
import com.example.project.security.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.net.http.HttpRequest;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    @Autowired
    private VariantRepository variantRepository;
    @Autowired
    private ProductRepository productRepository;

    // Lấy danh sách đơn hàng (phân trang)
    public Page<OrderDto> getOrderPage(String query, Pageable pageable){
        return orderRepository.getOrderDtosPage(query, pageable);
    }

    // Lấy danh sách các trang
    public List<Integer> getPageNumbers(int totalPages){
        return IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
    }

    @Transactional
    public OrderEntity createOrder(OrderCreateRequest request){
        // Tạo đơn hàng
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
        createOrderItems(request, order);

        return order;
    }

    // Tạo order items
    public List<OrderItemEntity> createOrderItems(OrderCreateRequest request, OrderEntity order){
        List<OrderItemEntity> items = request.getOrderItems();
        items.forEach(item -> {
            ProductEntity product = productRepository.findById(item.getProductId()).get();
            VariantEntity variant = variantRepository.findById(item.getVariantId()).get();

            item.setOrderEntity(order);
            item.setProductName(product.getName());
            item.setVariantSku(variant.getSku());
            item.setVariantColor(variant.getColor());
            item.setVariantSize(variant.getSize());

            // Cập nhật tồn kho phiên bản sp
            int varQty = (int) variant.getQuantity();
            int iteQty = (int) item.getQuantity();
            variant.setQuantity(varQty-iteQty);
            orderItemRepository.save(item);
        });
        return items;
    }

    // Set khách hàng cho order
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

    // Tìm đơn hàng theo id
    public OrderEntity getById(String id) {
        return orderRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Đơn hàng không tồn tại");
        });
    }
}
