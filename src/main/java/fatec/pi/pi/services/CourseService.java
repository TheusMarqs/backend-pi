package fatec.pi.pi.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fatec.pi.pi.dtos.course.CourseRequest;
import fatec.pi.pi.dtos.course.CourseResponse;
import fatec.pi.pi.entities.Course;
import fatec.pi.pi.mappers.CourseMapper;
import fatec.pi.pi.repositories.CourseRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class CourseService {
    @Autowired
    private CourseRepository repository;

    public List<CourseResponse> getCourseResponses() {
        List<Course> courses = repository.findAll();
        return courses.stream()
                .map(CourseMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CourseResponse getCourseResponse(long id) {
        Course course = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));
        return CourseMapper.toDTO(course);
    }

    public void deleteCourseById(long id) {
        if (this.repository.existsById(id)) {
            this.repository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Course not found");
        }
    }

    public CourseResponse save(CourseRequest course) {
        var entity = this.repository.save(CourseMapper.toEntity(course));
        return CourseMapper.toDTO(entity);
    }

    public void update(long id, CourseRequest course) {
        try {
            var updateCourse = this.repository.getReferenceById(id);
            updateCourse.setName(course.name());
            updateCourse.setWorkload(Integer.parseInt(course.workload()));
            updateCourse.setDuration(Integer.parseInt(course.duration()));
            this.repository.save(updateCourse);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Course not found");
        }
    }
}
