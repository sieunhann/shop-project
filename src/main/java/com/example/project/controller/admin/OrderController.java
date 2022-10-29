package com.example.project.controller.admin;

import com.example.project.dto.AccountDto;
import com.example.project.dto.OrderDto;
import com.example.project.repository.OrderRepository;
import com.example.project.request.OrderCreateRequest;
import com.example.project.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
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

    @GetMapping("/admin/order/create")
    public String getOrderCreatePage(){
        return "/admin/orders/admin-order-create";
    }

    @PostMapping(value = "/api/v1/admin/order")
    public ResponseEntity<?> createOrder(@RequestBody OrderCreateRequest request){
        return ResponseEntity.ok(orderService.createOrder(request));
    }

}
