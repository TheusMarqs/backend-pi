package fatec.pi.pi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fatec.pi.pi.entities.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
    
}