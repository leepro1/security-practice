package com.leepro1.security_jwt_server.service;

import com.leepro1.security_jwt_server.dto.CustomUserDetails;
import com.leepro1.security_jwt_server.entity.User;
import com.leepro1.security_jwt_server.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findByEmail(username);

        return user.map(CustomUserDetails::new).orElse(null);

    }
}
