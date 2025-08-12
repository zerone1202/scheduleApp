package org.example.scheduleapp.schedule.dto;

import lombok.Getter;

@Getter
public class ScheduleUpdateRequest {

    private String title;
    private String author;
    private String password;
}
