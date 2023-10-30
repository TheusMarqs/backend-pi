package fatec.pi.pi.mappers;

import fatec.pi.pi.dtos.course.CourseRequest;
import fatec.pi.pi.dtos.course.CourseResponse;
import fatec.pi.pi.entities.Course;

public class CourseMapper {
    public static Course toEntity(CourseRequest request) {
        Course course = new Course();
        course.setName(request.name());
        course.setWorkload(Integer.parseInt(request.workload()));
        course.setDuration(Integer.parseInt(request.duration()));
        return course;
    }

    public static CourseResponse toDTO(Course course) {
        return new CourseResponse(
            course.getId(),
            course.getName(),
            course.getWorkload(),
            course.getDuration()
        );
    }
}
