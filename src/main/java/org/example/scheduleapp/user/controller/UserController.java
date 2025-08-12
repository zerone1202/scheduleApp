package org.example.scheduleapp.user.controller;

import lombok.RequiredArgsConstructor;
import org.example.scheduleapp.user.dto.UserGetAllResponse;
import org.example.scheduleapp.user.dto.UserGetResponse;
import org.example.scheduleapp.user.dto.UserSaveRequest;
import org.example.scheduleapp.user.dto.UserSaveResponse;
import org.example.scheduleapp.user.entity.User;
import org.example.scheduleapp.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // 전체 유저 조회
    @GetMapping("/users")
    public ResponseEntity<List<UserGetAllResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    // 선택 유저 조회
    @GetMapping("/users/{userId}")
    public ResponseEntity<UserGetResponse> getOneUser(
            @PathVariable Long userId
    ) {
        return  ResponseEntity.ok(userService.findOne(userId));
    }
}
