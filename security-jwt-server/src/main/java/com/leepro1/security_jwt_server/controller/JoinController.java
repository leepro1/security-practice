package com.leepro1.security_jwt_server.controller;

import com.leepro1.security_jwt_server.dto.JoinDto;
import com.leepro1.security_jwt_server.service.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JoinController {

    private final JoinService joinService;

    @PostMapping("/join")
    public String join(@RequestBody JoinDto joinDto) {
        joinService.join(joinDto);

        return "success";
    }
}
