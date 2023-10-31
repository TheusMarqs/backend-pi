package fatec.pi.pi.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fatec.pi.pi.dtos.team.TeamRequest;
import fatec.pi.pi.dtos.team.TeamResponse;
import fatec.pi.pi.entities.Team;
import fatec.pi.pi.mappers.TeamMapper;
import fatec.pi.pi.repositories.TeamRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class TeamService {
    @Autowired
    private TeamRepository repository;

    public List<TeamResponse> getTeamResponses() {
        List<Team> teams = repository.findAll();
        return teams.stream()
                .map(TeamMapper::toDTO)
                .collect(Collectors.toList());
    }

    public TeamResponse getTeamResponse(long id) {
        Team team = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Team not found"));
        return TeamMapper.toDTO(team);
    }

    public void deleteTeamById(long id) {
        if (this.repository.existsById(id)) {
            this.repository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Team not found");
        }
    }

    public TeamResponse save(TeamRequest team) {
        var entity = this.repository.save(TeamMapper.toEntity(team));
        return TeamMapper.toDTO(entity);
    }

    public void update(long id, TeamRequest team) {
        try {
            var updateTeam = this.repository.getReferenceById(id);
            updateTeam.setCourse(Integer.parseInt(team.course()));
            updateTeam.setStudents(Integer.parseInt(team.students()));
            updateTeam.setSemester(Integer.parseInt(team.semester()));
            updateTeam.setPeriod(team.period());
            this.repository.save(updateTeam);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Team not found");
        }
    }
}
