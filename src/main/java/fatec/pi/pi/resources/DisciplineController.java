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

import fatec.pi.pi.dtos.discipline.DisciplineRequest;
import fatec.pi.pi.dtos.discipline.DisciplineResponse;
import fatec.pi.pi.services.DisciplineService;

@RestController
@RequestMapping("disciplines")
@CrossOrigin
public class DisciplineController {
    @Autowired
    private DisciplineService service;

    @GetMapping
    public ResponseEntity<List<DisciplineResponse>> getDisciplines() {
        var disciplines = this.service.getDisciplineResponses();
        return ResponseEntity.ok(disciplines);
    }

    @GetMapping("{id}")
    public ResponseEntity<DisciplineResponse> getTeam(@PathVariable long id) {
        var disciplines = this.service.getDisciplineResponse(id);
        return ResponseEntity.ok(disciplines);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteDiscipline(@PathVariable long id) {
        this.service.deleteDisciplineById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<DisciplineResponse> save(@Validated @RequestBody DisciplineRequest discipline) {
        var savedDiscipline = this.service.save(discipline);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedDiscipline.id())
                .toUri();
        return ResponseEntity.created(location).body(savedDiscipline);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@RequestBody DisciplineRequest discipline,
                                       @PathVariable long id
    ){
        this.service.update(id, discipline);
        return ResponseEntity.ok().build();
    }
}
