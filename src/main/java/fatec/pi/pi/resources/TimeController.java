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

import fatec.pi.pi.dtos.time.TimeRequest;
import fatec.pi.pi.dtos.time.TimeResponse;
import fatec.pi.pi.services.TimeService;

@RestController
@RequestMapping("times")
@CrossOrigin
public class TimeController {
    @Autowired
    private TimeService service;

    @GetMapping
    public ResponseEntity<List<TimeResponse>> getTimes() {
        var times = this.service.getTimeResponses();
        return ResponseEntity.ok(times);
    }

    @GetMapping("{id}")
    public ResponseEntity<TimeResponse> getTeam(@PathVariable long id) {
        var time = this.service.getTimeResponse(id);
        return ResponseEntity.ok(time);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteTime(@PathVariable long id) {
        this.service.deleteTimeById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<TimeResponse> save(@Validated @RequestBody TimeRequest time) {
        var savedTime = this.service.save(time);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedTime.id())
                .toUri();
        return ResponseEntity.created(location).body(savedTime);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@RequestBody TimeRequest time,
                                       @PathVariable long id
    ){
        this.service.update(id, time);
        return ResponseEntity.ok().build();
    }
}
