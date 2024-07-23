package com.leepro1.security_server.service;

import com.leepro1.security_server.dto.JoinDto;
import com.leepro1.security_server.entity.User;
import com.leepro1.security_server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void join(JoinDto joinDto) {

        if (userRepository.existsUserByEmail(joinDto.getEmail())) {
            throw new RuntimeException("중복 회원");
        }

        User user = new User();
        user.setEmail(joinDto.getEmail());
        user.setUsername(joinDto.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(joinDto.getPassword()));
        user.setRole("ROLE_ADMIN");

        userRepository.save(user);

    }

}
