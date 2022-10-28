package com.example.project.service;

import com.example.project.dto.AccountDto;
import com.example.project.entity.AccountEntity;
import com.example.project.exception.BadRequestException;
import com.example.project.repository.AccountRepository;
import com.example.project.request.RegisterRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class AccountService implements UserDetailsService {
    @Autowired
    private MailService mailService;
    @Autowired
    private AccountRepository accountRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return accountRepository.findByEmail(email).orElseThrow(() -> {
            throw new UsernameNotFoundException("Not found user with email = " + email);
        });
    }

    // Lấy danh sách các trang
    public List<Integer> getPageNumbers(int totalPages){
        return IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
    }

    // Lấy danh sách toàn bộ nhân viên và phân trang
    public Page<AccountDto> getAccounts(String name, Pageable pageable){
        return accountRepository.findAccountDtos(name, pageable);
    }


    // Lấy danh sách khách hàng + phân trang
    public Page<AccountDto> getAllCustomers(String phone, Pageable pageable){
        return accountRepository.findCustomers(phone, pageable);
    }


    // Reset mật khẩu
    public void resetPassword(String email){
        Optional<AccountEntity> accountOptional = accountRepository.findByEmail(email);
        if(accountOptional.isPresent()){
            String newPassword = UUID.randomUUID().toString().substring(0, 10);
            AccountEntity account = accountOptional.get();
            account.setPassword(newPassword);
            accountRepository.save(account);
            mailService.send(email, "Mật khẩu đăng nhập mới", newPassword);
            return;
        }
            throw new BadRequestException("Email chưa được đăng kí");
    }
}
