package com.example.project.service.order;

import com.example.project.dto.OrderDto;
import com.example.project.entity.account.AccountEntity;
import com.example.project.entity.order.*;
import com.example.project.entity.product.ProductEntity;
import com.example.project.entity.product.VariantEntity;
import com.example.project.exception.BadRequestException;
import com.example.project.exception.NotFoundException;
import com.example.project.repository.account.AccountRepository;
import com.example.project.repository.order.OrderItemRepository;
import com.example.project.repository.order.OrderRepository;
import com.example.project.repository.order.ShippingAddressRepository;
import com.example.project.repository.product.ProductRepository;
import com.example.project.repository.product.VariantRepository;
import com.example.project.request.OrderCreateRequest;
import com.example.project.request.OrderUpdateRequest;
import com.example.project.security.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
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
            variantRepository.save(variant);
            orderItemRepository.save(item);
        });
        return items;
    }

    // Set khách hàng cho order
    public AccountEntity getCustomer(HttpServletRequest request){
        // Lấy token từ trong header của request
        String token = jwtUtils.getTokenFromCookie(request);

        if(token != null){
            // Parse thông tin từ token
            Claims claims = jwtUtils.getClaimsFromToken(token);

            // Lấy username (email khách hàng)
            String userName = claims.getSubject();

            // Lấy thông tin khách hàng qua email
            AccountEntity customer = accountRepository.findByEmail(userName).orElseThrow(() -> {
                throw new NotFoundException("User không tồn tại");
            });

            return customer;
        } else {
            return null;
        }
    }

    // Tìm đơn hàng theo id
    public OrderEntity getById(String id) {
        return orderRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Đơn hàng không tồn tại");
        });
    }

    // Cập nhật đơn hàng
    public OrderEntity updateOrder(String id, OrderUpdateRequest request) {
        OrderEntity order = orderRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Đơn hàng không tồn tại");
        });
        order.setNote(request.getNote());
        order.setStatus(OrderStatus.valueOf(request.getStatus()));
        order.setPayment(OrderPayment.valueOf(request.getPayment()));
        order.setFulfillment(OrderFulfillment.valueOf(request.getFulfillment()));

        if(OrderStatus.valueOf(request.getStatus()) == OrderStatus.CANCELED){
            cancelOrder(order);
        }

        orderRepository.save(order);
        return order;
    }

    // Tìm đơn hàng theo khách hàng và phân trang
    public Page<OrderEntity> getByCustomerPagination(Long id, Pageable pageable){
        return orderRepository.findByCustomerPagination(id, pageable);
    }

    // Hủy đơn hàng
    public void cancelOrder(OrderEntity order){
        List<OrderItemEntity> items = order.getOrderItemEntities();
        items.forEach(item -> {
            VariantEntity variant = variantRepository.findById(item.getVariantId()).get();

            // Cập nhật tồn kho phiên bản sp
            int varQty = (int) variant.getQuantity();
            int iteQty = (int) item.getQuantity();
            variant.setQuantity(varQty + iteQty);
            variantRepository.save(variant);
            orderItemRepository.save(item);
        });
    }

    // Tạo đơn hàng trên Web
    @Transactional
    public OrderEntity createOrderWeb(HttpServletRequest request, OrderCreateRequest orderRequest) {

        OrderEntity order = OrderEntity.builder()
                .note(orderRequest.getNote())
                .total(orderRequest.getTotal())
                .accountEntity(getCustomer(request))
                .build();
        orderRepository.save(order);

        createOderItem(order, orderRequest);

        ShippingAddressEntity shippingAddress = orderRequest.getShippingAddress();
        shippingAddress.setOrderEntity(order);
        shippingAddressRepository.save(shippingAddress);

        return order;
    }

    public void createOderItem(OrderEntity order, OrderCreateRequest orderRequest){
        List<OrderItemEntity> orderItems = orderRequest.getOrderItems();
        orderItems.forEach(item -> {
            VariantEntity variant = variantRepository.findById(item.getVariantId()).orElseThrow(() -> {
                throw new NotFoundException("Không tồn tại " + item.getVariantId());
            });
            int varQty = (int) variant.getQuantity();
            int itemQty = (int) item.getQuantity();
            if(itemQty > varQty){
                throw new BadRequestException("Số lượng tồn kho của sản phẩm" + item.getProductName()
                        + "(" + item.getVariantSize() + "/" + item.getVariantColor() + ") không đủ");
            } else {
                variant.setQuantity(varQty - itemQty);
                variantRepository.save(variant);

                item.setOrderEntity(order);
                orderItemRepository.save(item);
            }
        });
    }
}
