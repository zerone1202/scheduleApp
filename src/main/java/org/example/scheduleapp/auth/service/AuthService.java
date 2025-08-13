package org.example.scheduleapp.auth.service;

import lombok.RequiredArgsConstructor;
import org.example.scheduleapp.auth.dto.AuthRequest;
import org.example.scheduleapp.user.entity.User;
import org.example.scheduleapp.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    @Transactional
    public void signup(AuthRequest request) {
        User user = new User(
                request.getUsername(),
                request.getEmail(),
                request.getPassword()
        );
        userRepository.save(user);
    }
}
