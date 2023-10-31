package fatec.pi.pi.mappers;

import fatec.pi.pi.dtos.discipline.DisciplineRequest;
import fatec.pi.pi.dtos.discipline.DisciplineResponse;
import fatec.pi.pi.entities.Discipline;

public class DisciplineMapper {
    public static Discipline toEntity(DisciplineRequest request) {
        Discipline discipline = new Discipline();
        discipline.setCourse(Integer.parseInt(request.course()));
        discipline.setName(request.name());
        discipline.setWorkload(Integer.parseInt(request.workload()));
        return discipline;
    }

    public static DisciplineResponse toDTO(Discipline discipline) {
        return new DisciplineResponse(
            discipline.getId(),
            discipline.getCourse(),
            discipline.getName(),
            discipline.getWorkload()
        );
    }
}
