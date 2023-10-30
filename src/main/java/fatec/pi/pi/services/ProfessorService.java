package fatec.pi.pi.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fatec.pi.pi.dtos.professor.ProfessorRequest;
import fatec.pi.pi.dtos.professor.ProfessorResponse;
import fatec.pi.pi.entities.Professor;
import fatec.pi.pi.mappers.ProfessorMapper;
import fatec.pi.pi.repositories.ProfessorRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ProfessorService {
    @Autowired
    private ProfessorRepository repository;

    public List<ProfessorResponse> getProfessorResponses() {
        List<Professor> professors = repository.findAll();
        return professors.stream()
                .map(ProfessorMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ProfessorResponse getProfessorResponse(long id) {
        Professor professor = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Professor not found"));
        return ProfessorMapper.toDTO(professor);
    }

    public void deleteProfessorById(long id) {
        if (this.repository.existsById(id)) {
            this.repository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Professor not found");
        }
    }

    public ProfessorResponse save(ProfessorRequest professor) {
        var entity = this.repository.save(ProfessorMapper.toEntity(professor));
        return ProfessorMapper.toDTO(entity);
    }

    public void update(long id, ProfessorRequest professor) {
        try {
            var updateProfessor = this.repository.getReferenceById(id);
            updateProfessor.setName(professor.name());
            updateProfessor.setEmail(professor.email());
            updateProfessor.setPassword(professor.password());
            updateProfessor.setEducation(professor.education());
            updateProfessor.setCondition(professor.condition());
            this.repository.save(updateProfessor);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Professor not found");
        }
    }
}
