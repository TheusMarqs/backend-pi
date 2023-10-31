package fatec.pi.pi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fatec.pi.pi.entities.Discipline;

public interface DisciplineRepository extends JpaRepository<Discipline,Long>{
    
}
