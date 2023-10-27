package fatec.pi.pi.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fatec.pi.pi.dtos.classroom.ClassroomRequest;
import fatec.pi.pi.dtos.classroom.ClassroomResponse;
import fatec.pi.pi.entities.Classroom;
import fatec.pi.pi.mappers.ClassroomMapper;
import fatec.pi.pi.repositories.ClassroomRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ClassroomService {
    @Autowired
    private ClassroomRepository repository;

    public List<ClassroomResponse> getClassroomResponses() {
        List<Classroom> students = repository.findAll();
        return students.stream()
                .map(ClassroomMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ClassroomResponse getClassroomResponse(long id) {
        Classroom classroom = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Classroom not found"));
        return ClassroomMapper.toDTO(classroom);
    }

    public void deleteClassroomById(long id) {
        if (this.repository.existsById(id)) {
            this.repository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Classroom not found");
        }
    }

    public ClassroomResponse save(ClassroomRequest classroom) {
        var entity = this.repository.save(ClassroomMapper.toEntity(classroom));
        return ClassroomMapper.toDTO(entity);
    }

    public void update(long id, ClassroomRequest classroom) {
        try {
            var updateClassroom = this.repository.getReferenceById(id);
            updateClassroom.setNumber(Integer.parseInt(classroom.number()));
            updateClassroom.setType(classroom.type());
            updateClassroom.setCapacity(Integer.parseInt(classroom.capacity()));
            this.repository.save(updateClassroom);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Classroom not found");
        }
    }
}
