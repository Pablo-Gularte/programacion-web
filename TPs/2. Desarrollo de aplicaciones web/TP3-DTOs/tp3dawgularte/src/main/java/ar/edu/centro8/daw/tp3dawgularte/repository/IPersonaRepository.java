package ar.edu.centro8.daw.tp3dawgularte.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.centro8.daw.tp3dawgularte.model.Persona;

@Repository
public interface IPersonaRepository extends JpaRepository<Persona, Long>{

}
