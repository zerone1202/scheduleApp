package org.example.scheduleapp.schedule.dto;

import java.time.LocalDateTime;

public class ScheduleGetResponse {

    private final Long id;
    private final String title;
    private final String content;
    private final String author;
    private final LocalDateTime createAt;
    private final LocalDateTime modifiedAt;

    public ScheduleGetResponse(Long id, String title, String content, String author, LocalDateTime createAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.createAt = createAt;
        this.modifiedAt = modifiedAt;
    }
}
