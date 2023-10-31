package fatec.pi.pi.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fatec.pi.pi.dtos.discipline.DisciplineRequest;
import fatec.pi.pi.dtos.discipline.DisciplineResponse;
import fatec.pi.pi.entities.Discipline;
import fatec.pi.pi.mappers.DisciplineMapper;
import fatec.pi.pi.repositories.DisciplineRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class DisciplineService {
    @Autowired
    private DisciplineRepository repository;

    public List<DisciplineResponse> getDisciplineResponses() {
        List<Discipline> disciplines = repository.findAll();
        return disciplines.stream()
                .map(DisciplineMapper::toDTO)
                .collect(Collectors.toList());
    }

    public DisciplineResponse getDisciplineResponse(long id) {
        Discipline discipline = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Discipline not found"));
        return DisciplineMapper.toDTO(discipline);
    }

    public void deleteDisciplineById(long id) {
        if (this.repository.existsById(id)) {
            this.repository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Discipline not found");
        }
    }

    public DisciplineResponse save(DisciplineRequest discipline) {
        var entity = this.repository.save(DisciplineMapper.toEntity(discipline));
        return DisciplineMapper.toDTO(entity);
    }

    public void update(long id, DisciplineRequest discipline) {
        try {
            var updateDiscipline = this.repository.getReferenceById(id);
            updateDiscipline.setCourse(Integer.parseInt(discipline.course()));
            updateDiscipline.setName(discipline.name());
            updateDiscipline.setWorkload(Integer.parseInt(discipline.workload()));
            this.repository.save(updateDiscipline);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Discipline not found");
        }
    }
}
