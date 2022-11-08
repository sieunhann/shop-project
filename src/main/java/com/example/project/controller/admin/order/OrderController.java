package com.example.project.controller.admin.order;

import com.example.project.dto.OrderDto;
import com.example.project.repository.order.OrderRepository;
import com.example.project.request.OrderCreateRequest;
import com.example.project.request.OrderUpdateRequest;
import com.example.project.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    // Trang tất cả đơn hàng
    @GetMapping("/admin/orders")
    public String getOrdersPage(Model model,
                                @RequestParam(name = "query", required = false) String query,
                                @RequestParam(value = "page", defaultValue = "1")int currentPage,
                                @RequestParam(value = "limit", defaultValue = "4") int pageSize){
        Page<OrderDto> orderPage =
                orderService.getOrderPage(query, PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("orderPage", orderPage);
        model.addAttribute("currentPage", currentPage);
        int totalPages = orderPage.getTotalPages();
        if(totalPages > 0){
            model.addAttribute("pageNumbers", orderService.getPageNumbers(totalPages));
        }
        return "/admin/orders/admin-orders";
    }

    // Trang tạo đơn hàng
    @GetMapping("/admin/orders/create")
    public String getOrderCreatePage(){
        return "/admin/orders/admin-order-create";
    }


    // Tạo đơn hàng
    @PostMapping(value = "/api/v1/admin/orders/create")
    public ResponseEntity<?> createOrder(@RequestBody OrderCreateRequest request){
        return ResponseEntity.ok(orderService.createOrder(request));
    }

    // Trang chi tiết đơn hàng
    @GetMapping("/admin/orders/{id}")
    public String getOrderPage(@PathVariable String id){
        return "admin/orders/admin-order";
    }

    // Lấy thông tin đơn hàng theo id
    @GetMapping("/api/v1/orders/{id}")
    public ResponseEntity<?> getOrder(@PathVariable String id){
        return ResponseEntity.ok(orderService.getById(id));
    }

    // Cập nhật đơn hàng
    @PutMapping("/api/v1/orders/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable String id,
                                         @RequestBody OrderUpdateRequest request){
        return ResponseEntity.ok(orderService.updateOrder(id, request));
    }

}
