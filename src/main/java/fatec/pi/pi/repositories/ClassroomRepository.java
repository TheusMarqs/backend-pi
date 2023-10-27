package fatec.pi.pi.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import fatec.pi.pi.entities.Classroom;

public interface ClassroomRepository extends JpaRepository<Classroom,Long>{
    
}
