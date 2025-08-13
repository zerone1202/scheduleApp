package org.example.scheduleapp.auth.service;

import lombok.RequiredArgsConstructor;
import org.example.scheduleapp.auth.dto.AuthRequest;
import org.example.scheduleapp.auth.dto.AuthResponse;
import org.example.scheduleapp.user.entity.User;
import org.example.scheduleapp.user.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    // 회원가입 기능 구현
    @Transactional
    public void signup(AuthRequest request) {
        User user = new User(
                request.getUsername(),
                request.getEmail(),
                request.getPassword()
        );
        userRepository.save(user);
    }

    // 이메일과 비밀번호를 활용한 로그인 기능 구현
    // 예외처리: 이메일 없거나 비밀번호 틀릴 경우 401 반환
    @Transactional(readOnly = true)
    public AuthResponse login(AuthRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(
//                () -> new IllegalArgumentException("존재하지 않는 이메일입니다."));
                () -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "존재하지 않는 이메일입니다."));
        if (!user.getPassword().equals(request.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
//            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }
        return new AuthResponse(user.getId());
    }
}
