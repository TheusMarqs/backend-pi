package fatec.pi.pi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fatec.pi.pi.entities.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
    List<Schedule> findByWeekday(String weekday);
}