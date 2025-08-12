package org.example.scheduleapp.user.dto;

import lombok.Getter;

@Getter
public class UserSaveRequest {

    private String username;
    private String email;
    private String password;
}
