package ar.edu.centro8.desarrollo.proyectojpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ar.edu.centro8.desarrollo.proyectojpa.model.Persona;

@Repository
public interface IPersonaRepository extends JpaRepository<Persona,Long>{

}
