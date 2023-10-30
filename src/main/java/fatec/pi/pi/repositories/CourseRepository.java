package fatec.pi.pi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fatec.pi.pi.entities.Course;

public interface CourseRepository extends JpaRepository<Course,Long>{
    
}
