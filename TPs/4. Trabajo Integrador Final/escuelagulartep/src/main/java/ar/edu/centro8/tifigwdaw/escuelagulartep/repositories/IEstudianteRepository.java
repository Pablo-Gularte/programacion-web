package ar.edu.centro8.tifigwdaw.escuelagulartep.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.centro8.tifigwdaw.escuelagulartep.models.Estudiante;

@Repository
public interface IEstudianteRepository extends JpaRepository<Estudiante, Long> {

    List<Estudiante> findByGradoId(Long id);

}
