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
                        .usernameParameter("email")  // 여기서 이메일을 username으로 사용하도록 설정합니다.
                        .loginProcessingUrl("/login") // 로그인 한 정보를 post 방식으로 보낼 url
                        .permitAll()
                )
//                .csrf((csrf) -> csrf.disable())
                .sessionManagement((session) -> session
                        .sessionFixation().changeSessionId()
                        .maximumSessions(2) // 하나의 아이디에는 최대 2개만 로그인
                        .maxSessionsPreventsLogin(true) // true 면 새로운 로그인을 차단하고 false면 기존 로그인을 로그아웃
                );

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
