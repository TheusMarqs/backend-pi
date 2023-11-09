package fatec.pi.pi.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fatec.pi.pi.dtos.time.TimeRequest;
import fatec.pi.pi.dtos.time.TimeResponse;
import fatec.pi.pi.entities.Time;
import fatec.pi.pi.mappers.TimeMapper;
import fatec.pi.pi.repositories.TimeRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class TimeService {
    @Autowired
    private TimeRepository repository;

    public List<TimeResponse> getTimeResponses() {
        List<Time> times = repository.findAll();
        return times.stream()
                .map(TimeMapper::toDTO)
                .collect(Collectors.toList());
    }

    public TimeResponse getTimeResponse(long id) {
        Time time = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Time not found"));
        return TimeMapper.toDTO(time);
    }

    public void deleteTimeById(long id) {
        if (this.repository.existsById(id)) {
            this.repository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Time not found");
        }
    }

    public TimeResponse save(TimeRequest time) {
        var entity = this.repository.save(TimeMapper.toEntity(time));
        return TimeMapper.toDTO(entity);
    }

    public void update(long id, TimeRequest time) {
        try {
            var updateTime = this.repository.getReferenceById(id);
            updateTime.setTime(time.time());
            this.repository.save(updateTime);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Time not found");
        }
    }
}
