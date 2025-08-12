package org.example.scheduleapp.user.controller;

import lombok.RequiredArgsConstructor;
import org.example.scheduleapp.user.dto.UserSaveRequest;
import org.example.scheduleapp.user.dto.UserSaveResponse;
import org.example.scheduleapp.user.entity.User;
import org.example.scheduleapp.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 유저 생성
    @PostMapping("/users")
    public ResponseEntity<UserSaveResponse> saveUser(
            @RequestBody UserSaveRequest request
    ) {
        return ResponseEntity.ok(userService.saveUser(request));
    }
}
