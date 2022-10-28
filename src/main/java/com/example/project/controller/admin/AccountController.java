package com.example.project.controller.admin;

import com.example.project.dto.AccountDto;
import com.example.project.service.AccountService;
import com.example.project.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/admin")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private RoleService roleService;

    // Danh sách nhân viên
    @GetMapping("/accounts")
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


}
