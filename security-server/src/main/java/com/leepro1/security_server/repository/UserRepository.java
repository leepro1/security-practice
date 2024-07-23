package com.leepro1.security_server.repository;

import com.leepro1.security_server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsUserByEmail(String email);

    User findUserByEmail(String email);

    User findUserByUsername(String username);
}
