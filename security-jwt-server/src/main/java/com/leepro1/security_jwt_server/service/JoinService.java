package com.leepro1.security_jwt_server.service;

import com.leepro1.security_jwt_server.dto.JoinDto;
import com.leepro1.security_jwt_server.entity.User;
import com.leepro1.security_jwt_server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void join(JoinDto joinDto) {

        String email = joinDto.getEmail();
        String password = joinDto.getPassword();

        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("중복 아이디 입니다.");
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setRole("ROLE_ADMIN");

        userRepository.save(user);

    }

}
