package fatec.pi.pi.services;

import org.springframework.stereotype.Service;

import fatec.pi.pi.dtos.schedule.ScheduleRequest;
import fatec.pi.pi.dtos.schedule.ScheduleResponse;
import fatec.pi.pi.entities.Schedule;
import fatec.pi.pi.mappers.ScheduleMapper;
import fatec.pi.pi.repositories.ScheduleRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleService {

    private final ScheduleRepository repository;

    public ScheduleService(ScheduleRepository repository) {
        this.repository = repository;
    }

    public List<ScheduleResponse> getScheduleResponses() {
        List<Schedule> schedules = repository.findAll();
        return schedules.stream()
                .map(ScheduleMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ScheduleResponse getScheduleResponse(long id) {
        Schedule schedule = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Schedule not found"));

        // Adicione logs para verificar os dados recuperados
        System.out.println("Retrieving schedule with id: " + id);
        System.out.println("Retrieved schedule data: " + schedule);
        return ScheduleMapper.toDTO(schedule);
    }

    public void deleteScheduleById(long id) {
        if (this.repository.existsById(id)) {
            this.repository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Schedule not found");
        }
    }

    public List<ScheduleResponse> save(List<ScheduleRequest> scheduleRequests) {
        List<Schedule> schedules = scheduleRequests.stream()
                .map(ScheduleMapper::toEntity)
                .collect(Collectors.toList());
        List<Schedule> savedSchedules = repository.saveAll(schedules);
        return savedSchedules.stream()
                .map(ScheduleMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void update(long id, List<ScheduleRequest> schedules) {
        try {
            var updateSchedules = new ArrayList<Schedule>();
            for (ScheduleRequest scheduleRequest : schedules) {
                Schedule schedule = repository.getReferenceById(id);
                schedule.setWeekday(scheduleRequest.weekday());
                schedule.setTime(Integer.parseInt(scheduleRequest.time()));
                schedule.setProfessor(Integer.parseInt(scheduleRequest.professor()));
                schedule.setClassroom(Integer.parseInt(scheduleRequest.classroom()));
                schedule.setDiscipline(Integer.parseInt(scheduleRequest.discipline()));
                schedule.setTeam(Integer.parseInt(scheduleRequest.team()));
                updateSchedules.add(schedule);
            }
            this.repository.saveAll(updateSchedules);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Schedule not found");
        }
    }
}
