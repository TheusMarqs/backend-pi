package fatec.pi.pi.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fatec.pi.pi.dtos.schedule.ScheduleRequest;
import fatec.pi.pi.dtos.schedule.ScheduleResponse;
import fatec.pi.pi.entities.Schedule;
import fatec.pi.pi.mappers.ScheduleMapper;
import fatec.pi.pi.repositories.ScheduleRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository repository;

    public List<ScheduleResponse> getScheduleResponses() {
        List<Schedule> schedules = repository.findAll();
        return schedules.stream()
                .map(ScheduleMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ScheduleResponse getScheduleResponse(long id) {
        Schedule schedule = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Schedule not found"));
        return ScheduleMapper.toDTO(schedule);
    }

    public void deleteScheduleById(long id) {
        if (this.repository.existsById(id)) {
            this.repository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Schedule not found");
        }
    }

    public ScheduleResponse save(ScheduleRequest schedule) {
        var entity = this.repository.save(ScheduleMapper.toEntity(schedule));
        return ScheduleMapper.toDTO(entity);
    }

    public void update(long id, ScheduleRequest schedule) {
        try {
            var updateSchedule = this.repository.getReferenceById(id);
            updateSchedule.setWeekday(schedule.weekday());
            updateSchedule.setTime(Integer.parseInt(schedule.time()));
            updateSchedule.setProfessor(Integer.parseInt(schedule.professor()));
            updateSchedule.setClassroom(Integer.parseInt(schedule.classroom()));
            updateSchedule.setDiscipline(Integer.parseInt(schedule.discipline()));
            updateSchedule.setTeam(Integer.parseInt(schedule.team()));
            this.repository.save(updateSchedule);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Schedule not found");
        }
    }
}
