package fatec.pi.pi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fatec.pi.pi.entities.Team;

public interface TeamRepository extends JpaRepository<Team, Long>{
    
}
