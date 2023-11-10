package fatec.pi.pi.mappers;

import fatec.pi.pi.dtos.team.TeamRequest;
import fatec.pi.pi.dtos.team.TeamResponse;
import fatec.pi.pi.entities.Team;

public class TeamMapper {
    public static Team toEntity(TeamRequest request) {
        Team team = new Team();
        team.setCourse(Integer.parseInt(request.course()));
        team.setStudents(Integer.parseInt(request.students()));
        team.setSemester(Integer.parseInt(request.semester()));
        team.setPeriod(request.period());
        team.setTime(request.time());
        return team;
    }

    public static TeamResponse toDTO(Team team) {
        return new TeamResponse(
            team.getId(),
            team.getCourse(),
            team.getStudents(),
            team.getSemester(),
            team.getPeriod(),
            team.getTime()
        );
    }
}
