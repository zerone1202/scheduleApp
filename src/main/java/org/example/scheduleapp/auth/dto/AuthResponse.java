package org.example.scheduleapp.auth.dto;

import lombok.Getter;

@Getter
public class AuthResponse {

    private final Long userId;

    public  AuthResponse(Long userId) {
        this.userId = userId;
    }
}
