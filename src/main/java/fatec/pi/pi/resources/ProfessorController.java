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

import fatec.pi.pi.dtos.ProfessorRequest;
import fatec.pi.pi.dtos.ProfessorResponse;
import fatec.pi.pi.services.ProfessorService;

@RestController
@RequestMapping("professors")
@CrossOrigin
public class ProfessorController {
    @Autowired
    private ProfessorService service;

    @GetMapping
    public ResponseEntity<List<ProfessorResponse>> getProfessors() {
        var professors = this.service.getProfessorResponses();
        return ResponseEntity.ok(professors);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProfessorResponse> getProfessor(@PathVariable long id) {
        var professor = this.service.getProfessorResponse(id);
        return ResponseEntity.ok(professor);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProfessor(@PathVariable long id) {
        this.service.deleteProfessorById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<ProfessorResponse> save(@Validated @RequestBody ProfessorRequest professor) {
        var savedProfessor = this.service.save(professor);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedProfessor.id())
                .toUri();
        return ResponseEntity.created(location).body(savedProfessor);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@RequestBody ProfessorRequest professor,
                                       @PathVariable long id
    ){
        this.service.update(id, professor);
        return ResponseEntity.ok().build();
    }
}
