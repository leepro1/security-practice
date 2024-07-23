package com.leepro1.security_server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .authorizeHttpRequests((auth) -> auth
                .requestMatchers("/", "/login", "/join").permitAll() // 특정 경로에 작업을 주기 위한 지정
                .requestMatchers("admin").hasRole("ADMIN")
                .requestMatchers("/my/**").hasAnyRole("ADMIN", "USER") // 여러 권한 설정
                .anyRequest().authenticated()
            )
            .formLogin((login) -> login
                .loginPage("/login") // 로그인 할 수 있는 페이지로 redirect
                .loginProcessingUrl("/login") // 로그인 한 정보를 post 방식으로 보낼 url
                .permitAll()
            )
            .csrf((csrf) -> csrf.disable());

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
