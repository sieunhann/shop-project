package com.example.project.controller.admin.account;

import com.example.project.dto.AccountDto;
import com.example.project.entity.AccountEntity;
import com.example.project.entity.OrderEntity;
import com.example.project.service.AccountService;
import com.example.project.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CustomerController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private OrderService orderService;

    // Danh sách khách hàng
    @GetMapping("/admin/customers")
    public String getCustomers(Model model,
                                      @RequestParam(name = "phone", required = false) String phone,
                                      @RequestParam(name = "page", defaultValue = "1") int currentPage,
                                      @RequestParam(value = "limit", defaultValue = "8") int pageSize){

        Page<AccountDto> customerPage = accountService.getAllCustomers(phone, PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("customerPage", customerPage);
        model.addAttribute("currentPage", currentPage);

        int totalPages = customerPage.getTotalPages();
        if(totalPages > 0){
            model.addAttribute("pageNumbers", accountService.getPageNumbers(totalPages));
        }
        return "admin/accounts/admin-customers";
    }

    // Trang chi tiết khách hàng
    @GetMapping("/admin/customer/{id}")
    public String getCustomer(Model model,
                              @PathVariable Long id,
                              @RequestParam(name = "page", defaultValue = "1") int currentPage,
                              @RequestParam(value = "limit", defaultValue = "4") int pageSize){
        Page<OrderEntity> orderPage = orderService.getByCustomerPagination(id, PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("orderPage", orderPage);
        model.addAttribute("currentPage", currentPage);
        int totalPages = orderPage.getTotalPages();
        if(totalPages > 0){
            model.addAttribute("pageNumbers", orderService.getPageNumbers(totalPages));
        }
        return "admin/accounts/admin-customer";
    }

    @GetMapping("/api/v1/order/{id}/customer")
    public ResponseEntity<?> getByOrder(@PathVariable String id){
        return ResponseEntity.ok(accountService.getByOrder(id));
    }

    @GetMapping("/api/v1/customer/{id}")
    public ResponseEntity<?> getCustomer(@PathVariable Long id){
        return ResponseEntity.ok(accountService.getById(id));
    }

    @PutMapping("/api/v1/customer/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long id,
                                        @RequestBody AccountEntity request){
        return ResponseEntity.ok(accountService.updateCustomer(id, request));
    }

}
