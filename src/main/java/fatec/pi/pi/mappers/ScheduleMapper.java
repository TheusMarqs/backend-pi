package fatec.pi.pi.mappers;

import fatec.pi.pi.dtos.schedule.ScheduleRequest;
import fatec.pi.pi.dtos.schedule.ScheduleResponse;
import fatec.pi.pi.entities.Schedule;

public class ScheduleMapper {
    public static Schedule toEntity(ScheduleRequest request) {
        Schedule schedule = new Schedule();
        schedule.setWeekday(request.weekday());
        schedule.setTime(Integer.parseInt(request.time()));
        schedule.setProfessor(Integer.parseInt(request.professor()));
        schedule.setClassroom(Integer.parseInt(request.classroom()));
        schedule.setDiscipline(Integer.parseInt(request.discipline()));
        schedule.setTeam(Integer.parseInt(request.team()));
        return schedule;
    }

    public static ScheduleResponse toDTO(Schedule schedule) {
        return new ScheduleResponse(
            schedule.getId(),
            schedule.getWeekday(),
            schedule.getTime(),
            schedule.getProfessor(),
            schedule.getClassroom(),
            schedule.getDiscipline(),
            schedule.getTeam()
        );
    }
}
