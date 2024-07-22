package com.leeprp1.jwt_server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // 폼 기반 로그인 비활성화
        http.formLogin((login) -> login.disable());

        // http 기본 인증 비활성화
        http.httpBasic((basic) -> basic.disable());

        // CSRF 공격 방어 기능 비활성화
        http.csrf((csrf) -> csrf.disable());

        // 세션 인증 비활성화 (jwt 사용)
        http.sessionManagement((management) -> management
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

}
