package org.example.scheduleapp.schedule.repository;

import org.example.scheduleapp.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
}
