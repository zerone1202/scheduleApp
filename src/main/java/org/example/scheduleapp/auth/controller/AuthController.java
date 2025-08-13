package org.example.scheduleapp.auth.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.scheduleapp.auth.dto.AuthRequest;
import org.example.scheduleapp.auth.dto.AuthResponse;
import org.example.scheduleapp.auth.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // 회원가입
    // 회원가입 요청은 인증 처리에서 제외됨 (Filter에서 화이트리스트 처리)
    @PostMapping("/signup")
    public String signup(
            @RequestBody AuthRequest request
    ) {
        authService.signup(request);
        return "유저 회원가입에 성공했습니다.";
    }

    // 로그인
    // 로그인 요청은 인증 처리에서 제외됨 (Filter에서 화이트리스트 처리)
    // 로그인 성공 시 세션에 사용자 정보 저장 (Cookie/Session 활용)
    @PostMapping("/login")
    public String login(
            @RequestBody AuthRequest authrequest,
            HttpServletRequest httpServletRequest
    ) {
        AuthResponse result = authService.login(authrequest);

        HttpSession httpSession = httpServletRequest.getSession();
        httpSession.setAttribute("LOGIN_USER", result.getUserId()); // Session 저장
        return "유저 로그인에 성공했습니다.";
    }
}
