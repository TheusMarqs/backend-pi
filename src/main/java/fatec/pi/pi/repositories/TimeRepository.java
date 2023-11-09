package fatec.pi.pi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fatec.pi.pi.entities.Time;

public interface TimeRepository extends JpaRepository<Time, Long>{
    
}