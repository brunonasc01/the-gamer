package br.com.method.the.gamer.core.api.repository;

import br.com.method.the.gamer.core.api.model.Gamer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GamerRepository extends JpaRepository<Gamer, Integer> {
    
    Optional<Gamer> findByName(String name); 
}
