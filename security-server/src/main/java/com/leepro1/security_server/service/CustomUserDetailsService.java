package com.leepro1.security_server.service;

import com.leepro1.security_server.dto.CustomUserDetails;
import com.leepro1.security_server.entity.User;
import com.leepro1.security_server.repository.UserRepository;
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
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email);

        if(user != null) {
            return new CustomUserDetails(user);
        }

        return null;
    }
}
