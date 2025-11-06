package ar.edu.centro8.desarrollo.proyectojpa1a1unidireccional.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.centro8.desarrollo.proyectojpa1a1unidireccional.models.Motor;

@Repository
public interface IMotorRepository extends JpaRepository<Motor, Long> {
    
}