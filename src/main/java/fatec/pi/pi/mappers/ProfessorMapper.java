package fatec.pi.pi.mappers;

import fatec.pi.pi.dtos.ProfessorRequest;
import fatec.pi.pi.dtos.ProfessorResponse;
import fatec.pi.pi.entities.Professor;

public class ProfessorMapper {
    public static Professor toEntity(ProfessorRequest request) {
        Professor professor = new Professor();
        professor.setName(request.name());
        professor.setEmail(request.email());
        professor.setPassword(request.password());
        professor.setEducation(request.education());
        professor.setCondition(request.condition());
        return professor;
    }

    public static ProfessorResponse toDTO(Professor professor) {
        return new ProfessorResponse(
                professor.getId(),
                professor.getName(),
                professor.getEmail(),
                professor.getPassword(),
                professor.getEducation(),
                professor.getCondition()
        );
    }
}
