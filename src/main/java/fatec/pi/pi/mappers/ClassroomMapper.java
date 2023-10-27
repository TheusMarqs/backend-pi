package fatec.pi.pi.mappers;

import fatec.pi.pi.dtos.classroom.ClassroomRequest;
import fatec.pi.pi.dtos.classroom.ClassroomResponse;
import fatec.pi.pi.entities.Classroom;

public class ClassroomMapper {
    public static Classroom toEntity(ClassroomRequest request) {
        Classroom classroom = new Classroom();
        classroom.setNumber(Integer.parseInt(request.number()));
        classroom.setType(request.type());
        classroom.setCapacity(Integer.parseInt(request.capacity()));
        return classroom;
    }

    public static ClassroomResponse toDTO(Classroom classroom) {
        return new ClassroomResponse(
            classroom.getId(),
            classroom.getNumber(),
            classroom.getType(),
            classroom.getCapacity()
        );
    }
}
