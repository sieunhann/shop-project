package com.example.project.controller.admin.account;

import com.example.project.dto.AccountDto;
import com.example.project.entity.RoleEntity;
import com.example.project.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/admin/roles")
    public String getRolesPage(Model model,
                              @RequestParam("page")Optional<Integer> page,
                              @RequestParam("limit") Optional<Integer> size){
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);

        Page<RoleEntity> rolePage = roleService.getAllRoles(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("rolePage", rolePage);
        model.addAttribute("currentPage", currentPage);
        int totalPages = rolePage.getTotalPages();
        if(totalPages > 0){
            model.addAttribute("pageNumbers", roleService.getPageNumbers(totalPages));
        }
        return "admin/accounts/admin-roles";
    }

    @GetMapping("/api/v1/roles")
    public ResponseEntity<?> getAllRoles(){
        return ResponseEntity.ok(roleService.getAllRoles());
    }
}
