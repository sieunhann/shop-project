package com.example.project.controller.admin;

import com.example.project.dto.AccountDto;
import com.example.project.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CustomerController {
    @Autowired
    private AccountService accountService;

    // Danh sách khách hàng
    @GetMapping("/admin/customers")
    public String getCustomers(Model model,
                                      @RequestParam(name = "phone", required = false) String phone,
                                      @RequestParam(name = "page", defaultValue = "1") int currentPage,
                                      @RequestParam(value = "limit", defaultValue = "8") int pageSize){

        Page<AccountDto> customerPage = accountService.getAllCustomers(phone, PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("customerPage", customerPage);
        model.addAttribute("customerPage", customerPage);
        model.addAttribute("currentPage", currentPage);

        int totalPages = customerPage.getTotalPages();
        if(totalPages > 0){
            model.addAttribute("pageNumbers", accountService.getPageNumbers(totalPages));
        }
        return "admin/accounts/admin-customers";
    }

    @GetMapping("/admin/customer")
    public String getCustomer(){
        return "admin/accounts/admin-customer";
    }
}
