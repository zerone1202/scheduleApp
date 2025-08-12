package org.example.scheduleapp.schedule.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.scheduleapp.schedule.dto.*;
import org.example.scheduleapp.schedule.entity.Schedule;
import org.example.scheduleapp.schedule.repository.ScheduleRepository;
import org.example.scheduleapp.user.entity.User;
import org.example.scheduleapp.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Transactional
    public ScheduleSaveResponse saveSchedule(ScheduleSaveRequest request, User user) {
        User createUser = userRepository.findByUsername(request.getUsername()).orElseThrow(
                () -> new EntityNotFoundException("해당 username의 유저를 찾을 수 없습니다."));

        Schedule schedule = new Schedule(
                request.getTitle(),
                request.getContent(),
                request.getPassword(),
                createUser
                );
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new ScheduleSaveResponse(
                savedSchedule.getId(),
                savedSchedule.getTitle(),
                savedSchedule.getContent(),
                createUser.getUsername(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getModifiedAt()
        );
    }

    @Transactional(readOnly = true)
    public List<ScheduleGetAllResponse> findAll(String username) {

        List<Schedule> schedules = scheduleRepository.findAll();
        List<ScheduleGetAllResponse> dtos = new ArrayList<>();

        // username 파라미터가 존재하지 않는 경우
        if (username == null) {
            for (Schedule schedule : schedules) {
                dtos.add(new ScheduleGetAllResponse(
                        schedule.getId(),
                        schedule.getTitle(),
                        schedule.getContent(),
                        username,
                        schedule.getCreatedAt(),
                        schedule.getModifiedAt()
                ));
            }
            return dtos;
        }

        // username 파라미터가 존재하는 경우
        for (Schedule schedule : schedules) {
            if (username.equals(schedule.getUser().getUsername())) {
                dtos.add(new ScheduleGetAllResponse(
                        schedule.getId(),
                        schedule.getTitle(),
                        schedule.getContent(),
                        username,
                        schedule.getCreatedAt(),
                        schedule.getModifiedAt()
                ));
            }
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    public ScheduleGetResponse findOne(long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new EntityNotFoundException("해당 id의 일정을 찾을 수 없습니다.")
        );
        String username = schedule.getUser().getUsername();

        return new ScheduleGetResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                username,
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    @Transactional
    public ScheduleUpdateResponse update(long scheduleId, ScheduleUpdateRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new EntityNotFoundException("해당 id의 일정을 찾을 수 없습니다.")
        );

        // 일정 수정 시, 선택한 일정의 비밀번호와 요청할 때 함께 보낸 비밀번호가 일치할 경우에만 가능
        if (!ObjectUtils.nullSafeEquals(schedule.getPassword(), request.getPassword())) {
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }

        // 더티체킹
        schedule.updateTitle(
                request.getTitle());

        return new ScheduleUpdateResponse(
                schedule.getTitle());
    }

    @Transactional
    public void deleteOne(long scheduleId, String password) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new EntityNotFoundException("해당 id의 일정을 찾을 수 없습니다.")
        );

        if (!ObjectUtils.nullSafeEquals(password, schedule.getPassword())) {
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }
        scheduleRepository.deleteById(scheduleId);
    }
}
