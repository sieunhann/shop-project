package com.example.project.controller.web;

import com.example.project.entity.account.AccountEntity;
import com.example.project.request.PasswordRequest;
import com.example.project.service.account.AccountService;
import com.example.project.service.order.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class WebDetailController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private OrderItemService orderItemService;

    @GetMapping("/api/v1/shop/detail")
    public ResponseEntity<?> getCustomerInfo(HttpServletRequest request){
        return ResponseEntity.ok(accountService.getDetail(request));
    }

    @GetMapping("api/v1/shop/detail/order")
    public ResponseEntity<?> getOrderByCustomer(HttpServletRequest request,
                                                @RequestParam(value = "page", defaultValue = "1") int currentPage,
                                                @RequestParam(value = "limit", defaultValue = "6") int pageSize){
        AccountEntity account = accountService.getDetail(request);
        return ResponseEntity.ok(accountService.getOrderByCustomer(account, PageRequest.of(currentPage - 1, pageSize), currentPage));
    }

    // Lấy thông tin chi tiết đơn hàng
    @GetMapping("/api/v1/shop/detail/order/{id}")
    public ResponseEntity<?> getByOrder(@PathVariable String id){
        return ResponseEntity.ok(orderItemService.getByOrder(id));
    }

    // Thay đổi mật khẩu
    @PutMapping("/api/v1/shop/detail/password")
    public ResponseEntity<?> changePassword(HttpServletRequest request,
                                            @RequestBody PasswordRequest passwordRequest){
        return ResponseEntity.ok(accountService.changePassword(request, passwordRequest));
    }

    @PutMapping("/api/v1/shop/detail")
    public ResponseEntity<?> updateCustomer(HttpServletRequest httpRequest,
                                            @RequestBody AccountEntity request){
        AccountEntity account = accountService.getDetail(httpRequest);
        Long id = account.getId();
        return ResponseEntity.ok(accountService.updateCustomer(id, request));
    }
}
