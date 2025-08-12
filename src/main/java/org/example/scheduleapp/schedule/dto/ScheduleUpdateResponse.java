package org.example.scheduleapp.schedule.dto;

import lombok.Getter;

@Getter
public class ScheduleUpdateResponse {

    private final String title;

    public ScheduleUpdateResponse(String title) {
        this.title = title;
    }
}
