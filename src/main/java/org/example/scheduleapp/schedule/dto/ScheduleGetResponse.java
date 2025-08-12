package org.example.scheduleapp.schedule.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleGetResponse {

    private final Long id;
    private final String title;
    private final String content;
    private final String username;
    private final LocalDateTime createAt;
    private final LocalDateTime modifiedAt;

    public ScheduleGetResponse(Long id, String title, String content, String username, LocalDateTime createAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.username = username;
        this.createAt = createAt;
        this.modifiedAt = modifiedAt;
    }
}
