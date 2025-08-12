package org.example.scheduleapp.user.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserSaveResponse {

    private final Long id;
    private final String userName;
    private final String email;
    private final LocalDateTime createAt;
    private final LocalDateTime modifiedAt;

    public UserSaveResponse(Long id, String username, String email, LocalDateTime createAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.userName = username;
        this.email = email;
        this.createAt = createAt;
        this.modifiedAt = modifiedAt;
    }
}
