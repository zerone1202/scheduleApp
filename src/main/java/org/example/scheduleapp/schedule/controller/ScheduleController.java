package org.example.scheduleapp.schedule.controller;

import lombok.RequiredArgsConstructor;
import org.example.scheduleapp.schedule.dto.*;
import org.example.scheduleapp.schedule.service.ScheduleService;
import org.example.scheduleapp.user.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 일정 생성
    @PostMapping("/schedules")
    public ResponseEntity<ScheduleSaveResponse> saveSchedule(
            @RequestBody ScheduleSaveRequest request, User user
    ) {
        return ResponseEntity.ok(scheduleService.saveSchedule(request, user));
    }

    // 전체 일정 조회
    @GetMapping("/schedules")
    public ResponseEntity<List<ScheduleGetAllResponse>> getSchedules(
            @RequestParam(required = false) String username
    ) {
        return ResponseEntity.ok(scheduleService.findAll(username));
    }

    // 선택 일정 조회
    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<ScheduleGetResponse> getSchedules(
            @PathVariable long scheduleId
    ) {
        return ResponseEntity.ok(scheduleService.findOne(scheduleId));
    }

    // 선택 일정 수정
    @PutMapping("/schedules/{scheduleId}")
    public ResponseEntity<ScheduleUpdateResponse> updateSchedules(
            @PathVariable long scheduleId,
            @RequestBody ScheduleUpdateRequest request
    ) {
        return ResponseEntity.ok(scheduleService.update(scheduleId, request));
    }

    // 선택 일정 삭제
    @DeleteMapping("/schedules/{scheduleId}")
    public void deleteSchedules(
            @PathVariable long scheduleId,
            @RequestParam String password
    ) {
        scheduleService.deleteOne(scheduleId, password);
    }
}

