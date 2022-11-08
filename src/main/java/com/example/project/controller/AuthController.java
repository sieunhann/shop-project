package com.example.project.controller;

import com.example.project.request.LoginRequest;
import com.example.project.request.RegisterRequest;
import com.example.project.service.account.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("api/v1/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request, HttpServletResponse httpServletResponse) {
        return authService.login(request, httpServletResponse);
    }

    @GetMapping("/logout-handle")
    public String login(HttpSession session) {
        return authService.logout(session);
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @GetMapping("/confirm")
    public String confirm(@RequestParam("token") String token) {
        return authService.confirmToken(token);
    }

}
