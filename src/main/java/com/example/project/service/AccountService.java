package com.example.project.service;

import com.example.project.dto.AccountDto;
import com.example.project.entity.AccountEntity;
import com.example.project.entity.OrderEntity;
import com.example.project.exception.BadRequestException;
import com.example.project.exception.NotFoundException;
import com.example.project.repository.AccountRepository;
import com.example.project.repository.OrderRepository;
import com.example.project.request.PasswordRequest;
import com.example.project.request.RegisterRequest;
import com.example.project.security.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class AccountService implements UserDetailsService {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private MailService mailService;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private OrderRepository orderRepository;

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

    // Reset mật khẩu
    public void resetPassword(String email){
        System.out.println(email);
        AccountEntity account = accountRepository.findByEmail(email).orElseThrow(() -> {
            throw new BadRequestException("Email chưa được đăng kí");
        });
            String newPassword = UUID.randomUUID().toString().substring(0, 10);
            try {
                mailService.send(email, "Mật khẩu đăng nhập mới", newPassword);
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                account.setPassword(encoder.encode(newPassword));
                accountRepository.save(account);
            } catch (MessagingException e) {
                throw new RuntimeException(e.getMessage());
            }
    }

    // ================ ACCOUNT ================

    // Lấy danh sách toàn bộ nhân viên và phân trang
    public Page<AccountDto> getAccounts(String name, Pageable pageable){
        return accountRepository.findAccountDtos(name, pageable);
    }

    // Tìm nvien theo id
    public AccountEntity getById(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Tài khoản không tồn tại");
        });
    }

    // Cập nhật thông tin nhân viên
    public AccountEntity updateAccount(Long id, AccountEntity request) {
        AccountEntity account = getById(id);
        account.setName(request.getName());
        account.setPhone(request.getPhone());
        account.setRoleEntities(request.getRoleEntities());
        accountRepository.save(account);
        return account;
    }

    // Xóa tài khoản nhân viên
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    // ================ CUSTOMER ================

    // Lấy danh sách khách hàng + phân trang
    public Page<AccountDto> getAllCustomers(String phone, Pageable pageable){
        return accountRepository.findCustomers(phone, pageable);
    }

    // Lấy thông tin khách hàng của đơn hàng
    public AccountEntity getByOrder(String id) {
        OrderEntity order = orderRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Đơn hàng không tồn tại");
        });
        return order.getAccountEntity();
    }

    public AccountEntity updateCustomer(Long id, AccountEntity request) {
        AccountEntity account = getById(id);
        account.setName(request.getName());
        account.setPhone(request.getPhone());
        account.setAddress(request.getAddress());
        account.setCity(request.getCity());
        accountRepository.save(account);
        return account;
    }

    // ================ DETAIL ================
    public AccountEntity getDetail(HttpServletRequest request){
        // Lấy token từ trong header của request
        String token = jwtUtils.getTokenFromCookie(request);

        // Parse thông tin từ token
        Claims claims = jwtUtils.getClaimsFromToken(token);

        // Lấy username (email khách hàng)
        String userName = claims.getSubject();

        // Lấy thông tin khách hàng qua email
        return accountRepository.findByEmail(userName).get();
    }

    // CHANGE PASSWORD
    public AccountEntity changePassword(HttpServletRequest request, PasswordRequest passwordRequest){
        // Lấy thông tin khách hàng qua email
        AccountEntity account = getDetail(request);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String oldPass = passwordRequest.getOldPassword();
        String newPass = passwordRequest.getNewPassword();
        if(oldPass.length() >= 3 && newPass.length() >= 3 && encoder.matches(oldPass, account.getPassword())){
            account.setPassword(encoder.encode(passwordRequest.getNewPassword()));
            accountRepository.save(account);
            return account;
        } else {
            throw new BadRequestException("Mật khẩu không chính xác");
        }
    }
}
