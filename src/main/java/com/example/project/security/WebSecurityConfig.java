package com.example.project.security;

import com.example.project.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final AccountService accountService;
    private final AuthenticationEntryPointCustom authenticationEntryPointCustom;
    private final AuthorizationFilterCustom authorizationFilterCustom;
    private final AccessDeniedHandlerCustom accessDeniedHandlerCustom;


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(accountService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                    .antMatchers("/admin", "/api/v1/auth/**", "/openapi/**", "/admin/plugins/**",
                            "/admin/custom/**", "/admin/dist/**", "/api/v1/detail/forgot",
                            "/api/v1/product/images/**", "/shop/**", "/api/v1/shop/**")
                        .permitAll()
                    .antMatchers("/admin/products/**", "/admin/product/**", "/api/v1/**",
                            "/admin/categories/**", "/admin/variants", "/admin/detail/**")
                        .hasAnyRole("INVENTORY", "ORDER", "CUSTOMER_CARE", "ADMIN")
                    .antMatchers("/admin/orders/**", "/admin/order/**",
                            "/admin/customer/**", "/admin/customers/**")
                        .hasAnyRole( "ORDER", "CUSTOMER_CARE", "ADMIN")
                    .antMatchers("/admin/**")
                        .hasRole("ADMIN")
                .and()
                    .exceptionHandling()
                    .authenticationEntryPoint(authenticationEntryPointCustom)
                    .accessDeniedHandler(accessDeniedHandlerCustom)
                .and()
                    .logout()
//                    .logoutUrl("/logout-handle")
                    .invalidateHttpSession(true)
                    .deleteCookies("JWT_COOKIE", "JSESSIONID")
//                    .logoutSuccessHandler((new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK)))
                    .logoutSuccessUrl("/admin")
                    .permitAll()
                .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .addFilterBefore(authorizationFilterCustom, UsernamePasswordAuthenticationFilter.class);
    }
}
