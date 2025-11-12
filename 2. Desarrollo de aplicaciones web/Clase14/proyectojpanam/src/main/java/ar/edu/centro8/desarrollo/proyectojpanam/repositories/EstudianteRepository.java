package ar.edu.centro8.desarrollo.proyectojpanam.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.centro8.desarrollo.proyectojpanam.models.Estudiante;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante,Long> {

}
