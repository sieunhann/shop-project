package com.example.project.repository;

import com.example.project.dto.AccountDto;
import com.example.project.entity.AccountEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    Optional<AccountEntity> findByEmail(String email);

    // Lấy danh sách account và phân trang
    @Query(nativeQuery = true, name = "getAccounts", countName = "getAccounts.count")
    Page<AccountDto> findAccountDtos(String name, Pageable pageable);


    // Lấy danh sách customer và phân trang
    @Query(nativeQuery = true, name = "getCustomers", countName = "getCustomers.count")
    Page<AccountDto> findCustomers(String phone, Pageable pageable);
}