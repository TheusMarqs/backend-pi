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

    public List<ScheduleResponse> getScheduleResponseByWeekday(int dayOfWeek) {
        // Realize a lógica de mapeamento do número para o dia da semana como uma String
        String weekday = mapNumberToWeekday(dayOfWeek);
    
        // Chame o método do repositório usando a String mapeada
        List<Schedule> schedules = repository.findByWeekday(weekday);
    
        if (schedules.isEmpty()) {
            throw new EntityNotFoundException("Schedules not found for weekday: " + weekday);
        }
    
        // Adicione logs para verificar os dados recuperados
        System.out.println("Retrieving schedules with weekday: " + weekday);
        schedules.forEach(schedule -> System.out.println("Retrieved schedule data: " + schedule));
    
        return schedules.stream()
                .map(ScheduleMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    private String mapNumberToWeekday(int dayOfWeek) {
        // Adapte esta lógica conforme necessário
        switch (dayOfWeek) {
            case 1:
                return "Segunda-feira";
            case 2:
                return "Terça-feira";
            case 3:
                return "Quarta-feira";
            case 4:
                return "Quinta-feira";
            case 5:
                return "Sexta-feira";
            case 6:
                return "Sábado";
            default:
                throw new IllegalArgumentException("Invalid day of week: " + dayOfWeek);
        }
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

    public void updateSchedulesByWeekday(int weekdayId, List<ScheduleRequest> schedules) {
        String weekday = mapNumberToWeekday(weekdayId);
        try {
            List<Schedule> existingSchedules = repository.findByWeekday(weekday);

            for (int i = 0; i < existingSchedules.size(); i++) {
                Schedule existingSchedule = existingSchedules.get(i);
                ScheduleRequest updatedScheduleRequest = schedules.get(i);

                // Atualize os atributos do agendamento conforme necessário
                existingSchedule.setTime(Integer.parseInt(updatedScheduleRequest.time()));
                existingSchedule.setProfessor(Integer.parseInt(updatedScheduleRequest.professor()));
                existingSchedule.setClassroom(Integer.parseInt(updatedScheduleRequest.classroom()));
                existingSchedule.setDiscipline(Integer.parseInt(updatedScheduleRequest.discipline()));
                existingSchedule.setTeam(Integer.parseInt(updatedScheduleRequest.team()));

                // Salve as alterações no agendamento
                repository.save(existingSchedule);
            }
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Schedules not found for the given weekday");
        }
    }
    
}
