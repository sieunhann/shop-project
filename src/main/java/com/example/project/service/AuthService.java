package com.example.project.service;

import com.example.project.entity.AccountEntity;
import com.example.project.entity.RoleEntity;
import com.example.project.entity.TokenEntity;
import com.example.project.exception.BadRequestException;
import com.example.project.exception.NotFoundException;
import com.example.project.repository.AccountRepository;
import com.example.project.repository.RoleRepository;
import com.example.project.repository.TokenRepository;
import com.example.project.request.LoginRequest;
import com.example.project.request.RegisterRequest;
import com.example.project.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.project.utils.Constant.MAX_AGE_COOKIE;


@Service
public class AuthService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MailService mailService;

    // LOGIN USER
    public String login(LoginRequest request, HttpServletResponse httpServletResponse) {
        try {
            // Tạo đối tượng xác thực dựa trên thông tin request
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

            // Tiến hành xác thực
            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            // Lưu trữ thông tin của đối tượng đã đăng nhập
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Generate JWT token
            String token = jwtUtils.generateToken((UserDetails) authentication.getPrincipal());

            // Lưu thông tin vào trong cookie (nếu không sử dụng cookie thì trả thẳng token về
            // cho client và mỗi request client gửi lên đều phải kèm token trong header của request)
            Cookie cookie = new Cookie("JWT_COOKIE", token);
            cookie.setPath("/");
            cookie.setMaxAge(MAX_AGE_COOKIE); // Thời gian hết hạn cookie
            cookie.setHttpOnly(true); // Không cho phép client chỉnh sửa thông tin trong cookie (read-only)

            httpServletResponse.addCookie(cookie);

            // Trả về token cho client
            return token;
        } catch (Exception ex) {
            throw new BadRequestException("Email hoặc mật khẩu không chính xác");
        }
    }

    // LOGOUT USER
    public String logout(HttpSession session) {
        session.invalidate();
        return "logout success";
    }

    // REGISTER USER
    public String register(RegisterRequest request) {
        // Lấy thông tin user dựa trên email
        Optional<AccountEntity> accountOptional = accountRepository.findByEmail(request.getEmail());

        if (accountOptional.isPresent()) {
            // Nếu user được tìm thấy có trùng các thuộc tính và chưa được kích hoạt
            // Gửi email kích hoạt
            AccountEntity account = accountOptional.get();
            if (!account.getEnabled()
                    && account.getName().equals(request.getName())
                    && account.getEmail().equals(request.getEmail())) {
                return generateTokenAndSendMail(account);
            }

            throw new BadRequestException("Email = " + request.getEmail() + " đã tồn tại");
        }

        // Mã hóa password
        String passwordEncode = passwordEncoder.encode(request.getPassword());

        // Tạo user và lưu vào database
        List<RoleEntity> roles = new ArrayList<>();
        request.getRoles().forEach(id -> roles.add(roleRepository.findById(id).get()));

        AccountEntity newAccount = new AccountEntity(request.getName(), request.getEmail(), request.getPhone(),
                passwordEncode, request.getAddress(), request.getCity(), roles);
        accountRepository.save(newAccount);

        // Sinh ra token
        return generateTokenAndSendMail(newAccount);
    }

    // SINH TOKEN - SEND MAIL
    private String generateTokenAndSendMail(AccountEntity account) {
        // Sinh ra token
        String tokenString = UUID.randomUUID().toString();

        // Tạo token và lưu token
        TokenEntity token = new TokenEntity(
                tokenString,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                account
        );
        tokenRepository.save(token);

        // Gửi email
        String link = "<a href=\"" + "http://localhost:8686/api/v1/auth/confirm?token="
                + tokenString + "\"> Kích hoạt tài khoản </a>";
        try {
            mailService.send(account.getEmail(), "Xác thực tài khoản", link);
        } catch (MessagingException e) {
            throw new RuntimeException(e.getMessage());
        }

        return link;
    }

    // VERIFY TOKEN
    public String confirmToken(String tokenString) {
        // Lấy thông tin của token
        TokenEntity token = tokenRepository.findByToken(tokenString).orElseThrow(() ->
                new NotFoundException("Không tìm thấy token")
        );

        // Xem token đã được confirm hay chưa
        if (token.getConfirmedAt() != null) {
            throw new BadRequestException("Token đã được xác thực");
        }

        // Xem token đã hết hạn chưa
        LocalDateTime expiredAt = token.getExpiresAt();
        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new BadRequestException("Token đã hết thời gian");
        }

        // Active token
        token.setConfirmedAt(LocalDateTime.now());
        tokenRepository.save(token);

        // Active user
        AccountEntity account = token.getAccountEntity();
        account.setEnabled(true);
        accountRepository.save(account);

        return "confirmed";
    }
}
