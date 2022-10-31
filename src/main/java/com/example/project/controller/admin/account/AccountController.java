package com.example.project.controller.admin.account;

import com.example.project.dto.AccountDto;
import com.example.project.entity.AccountEntity;
import com.example.project.request.PasswordRequest;
import com.example.project.service.AccountService;
import com.example.project.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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

    // Truy cập trang thông tin nhân viên
    @GetMapping("/admin/account/{id}")
    public String getAccountPage(@PathVariable Long id){
        return "/admin/accounts/admin-account";
    }

    // Lấy thông tin nhân viên
    @GetMapping("/api/v1/account/{id}")
    public ResponseEntity<?> getAccount(@PathVariable Long id){
        return ResponseEntity.ok(accountService.getById(id));
    }

    // Cập nhật thông tin nhân viên
    @PutMapping("/api/v1/account/{id}")
    public ResponseEntity<?> updateAccount(@PathVariable Long id,
                                        @RequestBody AccountEntity request){
        return ResponseEntity.ok(accountService.updateAccount(id, request));
    }

    // Xóa nhân viên
    @DeleteMapping("/api/v1/account/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }

    // Truy cập trang thông tin cá nhân
    @GetMapping("/admin/detail")
    public String getDetailPage(){
        return "admin/detail";
    }

    // Lấy thông tin cá nhân đang đăng nhập
    @GetMapping("/api/v1/detail")
    public ResponseEntity<?> getInfo(HttpServletRequest request){
        return ResponseEntity.ok(accountService.getDetail(request));
    }

    // Thay đổi mật khẩu
    @PutMapping("/api/v1/detail/password")
    public ResponseEntity<?> changePassword(HttpServletRequest request,
                                            @RequestBody PasswordRequest passwordRequest){
        return ResponseEntity.ok(accountService.changePassword(request, passwordRequest));
    }

    // Quên mật khẩu
    @PutMapping("/api/v1/detail/forgot")
    public ResponseEntity<?> forgotPassword(@RequestParam String email){
        accountService.resetPassword(email);
        return ResponseEntity.noContent().build();
    }
}
