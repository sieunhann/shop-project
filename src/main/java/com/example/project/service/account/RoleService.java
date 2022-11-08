package com.example.project.service.account;

import com.example.project.entity.account.RoleEntity;
import com.example.project.repository.account.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    // Lấy danh sách tất cả role trừ CUSTOMER
    public List<RoleEntity> getRolesExceptCustomer(){
        return roleRepository.findByIdIsNot(1L);
    }

    // Lấy danh sách tất cả role và phân trang
    public Page<RoleEntity> getAllRoles(Pageable pageable){
        return roleRepository.findAll(pageable);
    }

    public List<Integer> getPageNumbers(int totalPages) {
        return IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
    }

}
