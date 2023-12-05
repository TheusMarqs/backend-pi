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
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("{teamId}/{dayId}")
    public ResponseEntity<List<ScheduleResponse>> getSchedule(@PathVariable int teamId, @PathVariable int dayId) {
        var schedules = this.service.getScheduleResponseByWeekday(dayId);
        return ResponseEntity.ok(schedules);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable long id) {
        this.service.deleteScheduleById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<List<ScheduleResponse>> save(@Validated @RequestBody List<ScheduleRequest> scheduleRequests) {
        var savedSchedules = this.service.save(scheduleRequests);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedSchedules.get(0).id()) // Assuming the first schedule in the list
                .toUri();
        return ResponseEntity.created(location).body(savedSchedules);
    }

    @PutMapping("{weekdayId}")
    public ResponseEntity<Void> updateSchedulesByWeekday(@PathVariable int weekdayId,
            @RequestBody List<ScheduleRequest> schedules) {
        this.service.updateSchedulesByWeekday(weekdayId, schedules);
        return ResponseEntity.ok().build();
    }

}
