package org.example.scheduleapp.schedule.dto;

import lombok.Getter;

@Getter
public class ScheduleSaveRequest {

    private String title;
    private String content;
    private String username;
    private String password;
}
