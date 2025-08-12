package org.example.scheduleapp.schedule.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleSaveResponse {

    private final Long id;
    private final String title;
    private final String content;
    private final String userName;
    private final LocalDateTime createAt;
    private final LocalDateTime modifiedAt;

    public ScheduleSaveResponse(Long id, String title, String content, String userName, LocalDateTime createAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userName = userName;
        this.createAt = createAt;
        this.modifiedAt = modifiedAt;
    }
}
