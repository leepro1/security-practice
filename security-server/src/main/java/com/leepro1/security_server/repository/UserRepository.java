package com.leepro1.security_server.repository;

import com.leepro1.security_server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsUserByEmail(String email);

    Optional<User> findByEmail(String email);

}
