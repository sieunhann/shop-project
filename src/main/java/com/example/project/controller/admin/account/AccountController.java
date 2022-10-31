package com.example.project.controller.admin.account;

import com.example.project.dto.AccountDto;
import com.example.project.entity.AccountEntity;
import com.example.project.service.AccountService;
import com.example.project.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller

public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private RoleService roleService;

    // Danh sách nhân viên
    @GetMapping("/admin/accounts")
    public String getAllAccounts(Model model,
                                 @RequestParam(name = "name", required = false) String name,
                                 @RequestParam(value = "page", defaultValue = "1")int currentPage,
                                 @RequestParam(value = "limit", defaultValue = "4") int pageSize ){

        Page<AccountDto> accountPage =
                accountService.getAccounts(name, PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("accountPage", accountPage);
        model.addAttribute("currentPage", currentPage);
        int totalPages = accountPage.getTotalPages();
        if(totalPages > 0){
            model.addAttribute("pageNumbers", accountService.getPageNumbers(totalPages));
        }

        model.addAttribute("roles", roleService.getRolesExceptCustomer());

        return "admin/accounts/admin-accounts";
    }

    @GetMapping("/admin/account/{id}")
    public String getAccountPage(@PathVariable Long id){
        return "/admin/accounts/admin-account";
    }

    @GetMapping("/api/v1/account/{id}")
    public ResponseEntity<?> getAccount(@PathVariable Long id){
        return ResponseEntity.ok(accountService.getById(id));
    }

    @PutMapping("/api/v1/account/{id}")
    public ResponseEntity<?> updateAccount(@PathVariable Long id,
                                        @RequestBody AccountEntity request){
        return ResponseEntity.ok(accountService.updateAccount(id, request));
    }

    @DeleteMapping("/api/v1/account/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }
}
