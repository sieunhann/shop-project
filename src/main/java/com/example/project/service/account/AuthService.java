package com.example.project.service.account;

import com.example.project.entity.account.AccountEntity;
import com.example.project.entity.account.RoleEntity;
import com.example.project.entity.account.TokenEntity;
import com.example.project.exception.BadRequestException;
import com.example.project.exception.NotFoundException;
import com.example.project.repository.account.AccountRepository;
import com.example.project.repository.account.RoleRepository;
import com.example.project.repository.account.TokenRepository;
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
            // T???o ?????i t?????ng x??c th???c d???a tr??n th??ng tin request
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

            // Ti???n h??nh x??c th???c
            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            // L??u tr??? th??ng tin c???a ?????i t?????ng ???? ????ng nh???p
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Generate JWT token
            String token = jwtUtils.generateToken((UserDetails) authentication.getPrincipal());

            // L??u th??ng tin v??o trong cookie (n???u kh??ng s??? d???ng cookie th?? tr??? th???ng token v???
            // cho client v?? m???i request client g???i l??n ?????u ph???i k??m token trong header c???a request)
            Cookie cookie = new Cookie("JWT_COOKIE", token);
            cookie.setPath("/");
            cookie.setMaxAge(MAX_AGE_COOKIE); // Th???i gian h???t h???n cookie
            cookie.setHttpOnly(true); // Kh??ng cho ph??p client ch???nh s???a th??ng tin trong cookie (read-only)

            httpServletResponse.addCookie(cookie);

            // Tr??? v??? token cho client
            return token;
        } catch (Exception ex) {
            throw new BadRequestException("Email ho???c m???t kh???u kh??ng ch??nh x??c");
        }
    }

    // LOGOUT USER
    public String logout(HttpSession session) {
        session.invalidate();
        return "logout success";
    }

    // REGISTER USER
    public String register(RegisterRequest request) {
        // L???y th??ng tin user d???a tr??n email
        Optional<AccountEntity> accountOptional = accountRepository.findByEmail(request.getEmail());

        if (accountOptional.isPresent()) {
            // N???u user ???????c t??m th???y c?? tr??ng c??c thu???c t??nh v?? ch??a ???????c k??ch ho???t
            // G???i email k??ch ho???t
            AccountEntity account = accountOptional.get();
            if (!account.getEnabled()
                    && account.getName().equals(request.getName())
                    && account.getEmail().equals(request.getEmail())) {
                return generateTokenAndSendMail(account);
            }

            throw new BadRequestException("Email = " + request.getEmail() + " ???? t???n t???i");
        }

        // M?? h??a password
        String passwordEncode = passwordEncoder.encode(request.getPassword());

        // T???o user v?? l??u v??o database
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

        // T???o token v?? l??u token
        TokenEntity token = new TokenEntity(
                tokenString,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                account
        );
        tokenRepository.save(token);

        // G???i email
        String link = "<a href=\"" + "http://localhost:8888/api/v1/auth/confirm?token="
                + tokenString + "\"> K??ch ho???t t??i kho???n </a>";
        try {
            mailService.send(account.getEmail(), "X??c th???c t??i kho???n", link);
        } catch (MessagingException e) {
            throw new RuntimeException(e.getMessage());
        }

        return link;

    }

    // VERIFY TOKEN
    public String confirmToken(String tokenString) {
        // L???y th??ng tin c???a token
        TokenEntity token = tokenRepository.findByToken(tokenString).orElseThrow(() ->
                new NotFoundException("Kh??ng t??m th???y token")
        );

        // Xem token ???? ???????c confirm hay ch??a
        if (token.getConfirmedAt() != null) {
            throw new BadRequestException("Token ???? ???????c x??c th???c");
        }

        // Xem token ???? h???t h???n ch??a
        LocalDateTime expiredAt = token.getExpiresAt();
        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new BadRequestException("Token ???? h???t th???i gian");
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
