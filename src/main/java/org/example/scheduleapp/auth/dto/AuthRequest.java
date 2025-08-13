package org.example.scheduleapp.auth.dto;

import lombok.Getter;

@Getter
public class AuthRequest {

    private String username;
    private String email;
    private String password;
}
