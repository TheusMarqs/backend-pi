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

import fatec.pi.pi.dtos.schedule.ScheduleRequest;
import fatec.pi.pi.dtos.schedule.ScheduleResponse;
import fatec.pi.pi.services.ScheduleService;

@RestController
@RequestMapping("schedules")
@CrossOrigin
public class ScheduleController {
    @Autowired
    private ScheduleService service;

    @GetMapping
    public ResponseEntity<List<ScheduleResponse>> getSchedules() {
        var schedules = this.service.getScheduleResponses();
        return ResponseEntity.ok(schedules);
    }

    @GetMapping("{id}")
    public ResponseEntity<ScheduleResponse> getSchedule(@PathVariable long id) {
        var schedule = this.service.getScheduleResponse(id);
        return ResponseEntity.ok(schedule);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable long id) {
        this.service.deleteScheduleById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<ScheduleResponse> save(@Validated @RequestBody ScheduleRequest schedule) {
        var savedSchedule = this.service.save(schedule);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedSchedule.id())
                .toUri();
        return ResponseEntity.created(location).body(savedSchedule);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@RequestBody ScheduleRequest schedule,
                                       @PathVariable long id
    ){
        this.service.update(id, schedule);
        return ResponseEntity.ok().build();
    }
}
