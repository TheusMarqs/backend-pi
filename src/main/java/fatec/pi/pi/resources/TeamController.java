package fatec.pi.pi.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import fatec.pi.pi.dtos.team.TeamRequest;
import fatec.pi.pi.dtos.team.TeamResponse;
import fatec.pi.pi.services.TeamService;

@RestController
@RequestMapping("teams")
@CrossOrigin
public class TeamController {
    @Autowired
    private TeamService service;

    @GetMapping
    public ResponseEntity<List<TeamResponse>> getTeams() {
        var teams = this.service.getTeamResponses();
        return ResponseEntity.ok(teams);
    }

    @GetMapping("{id}")
    public ResponseEntity<TeamResponse> getTeam(@PathVariable long id) {
        var team = this.service.getTeamResponse(id);
        return ResponseEntity.ok(team);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable long id) {
        this.service.deleteTeamById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<TeamResponse> save(@Validated @RequestBody TeamRequest team) {
        var savedTeam = this.service.save(team);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedTeam.id())
                .toUri();
        return ResponseEntity.created(location).body(savedTeam);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@RequestBody TeamRequest course,
                                       @PathVariable long id
    ){
        this.service.update(id, course);
        return ResponseEntity.ok().build();
    }
}
