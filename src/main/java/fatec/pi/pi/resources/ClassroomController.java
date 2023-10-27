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

import fatec.pi.pi.dtos.classroom.ClassroomRequest;
import fatec.pi.pi.dtos.classroom.ClassroomResponse;
import fatec.pi.pi.services.ClassroomService;


@RestController
@RequestMapping("classrooms")
@CrossOrigin
public class ClassroomController {
    @Autowired
    private ClassroomService service;

    @GetMapping
    public ResponseEntity<List<ClassroomResponse>> getClassrooms() {
        var classrooms = this.service.getClassroomResponses();
        return ResponseEntity.ok(classrooms);
    }

    @GetMapping("{id}")
    public ResponseEntity<ClassroomResponse> getClassroom(@PathVariable long id) {
        var classroom = this.service.getClassroomResponse(id);
        return ResponseEntity.ok(classroom);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteClassroom(@PathVariable long id) {
        this.service.deleteClassroomById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<ClassroomResponse> save(@Validated @RequestBody ClassroomRequest classroom) {
        var savedClassroom = this.service.save(classroom);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedClassroom.id())
                .toUri();
        return ResponseEntity.created(location).body(savedClassroom);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@RequestBody ClassroomRequest classroom,
                                       @PathVariable long id
    ){
        this.service.update(id, classroom);
        return ResponseEntity.ok().build();
    }
}
