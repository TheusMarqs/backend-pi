package fatec.pi.pi.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import fatec.pi.pi.entities.Professor;

public interface ProfessorRepository extends JpaRepository<Professor,Long> {
    
}