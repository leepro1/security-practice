package com.leeprp1.jwt_server.controller;

import com.leeprp1.jwt_server.constants.SecurityConstants;
import com.leeprp1.jwt_server.domain.AuthenticationRequest;
import com.leeprp1.jwt_server.util.JwtProp;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class LoginController {

    @Autowired
    private JwtProp jwtProp;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest request) {

        String username = request.getUsername();
        String password = request.getPassword();

        System.out.println("username : " + username);
        System.out.println("password : " + password);

        // db 연결이 안되어 있기 때문에 임의로 권한 test
        List<String> roles = new ArrayList<>();
        roles.add("ROLE_USER");
        roles.add("ROLE_ADMIN");


        // 토큰 생성
        byte[] signingKey = jwtProp.getSecretKey().getBytes();

        String jwt = Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(signingKey), Jwts.SIG.HS512)
                .header()
                .add("typ", SecurityConstants.TOKEN_TYPE)
                .and()
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24 * 5))
                .claim("uid", username)
                .claim("rol", roles)
                .compact();

        System.out.println("jwt : " + jwt);

        return new ResponseEntity<String>(jwt, HttpStatus.OK);
    }

    @GetMapping("/user/info")
    public ResponseEntity<?> userInfo(@RequestHeader(name = "Authorization") String header) {
        System.out.println("Authorization : " + header);

        String jwt = header.replace(SecurityConstants.TOKEN_PREFIX, "");

        byte[] signingKey = jwtProp.getSecretKey().getBytes();

        Jws<Claims> parsedToken = Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(signingKey))
                .build()
                .parseSignedClaims(jwt);

        // 꺼내보기
        String username = parsedToken.getPayload().get("uid").toString();
        System.out.println("username : " + username);

        Claims claims = parsedToken.getPayload();
        Object roles = claims.get("rol");
        System.out.println("role : " + roles);

        return new ResponseEntity<String>(parsedToken.toString(), HttpStatus.OK);
    }

}
