package com.leepro1.security_jwt_server.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinDto {

    private String email;
    private String password;
}
