package com.leeprp1.jwt_server.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

// properties에서 secretkey
@Component
@ConfigurationProperties("com.leepro1.jwt") // 이 하위 경로의 속성들을 지정
public class JwtProp {

    private String secretKey;

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
