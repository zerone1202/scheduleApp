package org.example.scheduleapp.auth.controller;

import lombok.RequiredArgsConstructor;
import org.example.scheduleapp.auth.dto.AuthRequest;
import org.example.scheduleapp.auth.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // 회원가입
    @PostMapping("/signup")
    public String signup(
            @RequestBody AuthRequest request
    ) {
        authService.signup(request);
        return "회원가입에 성공했습니다.";
    }

}
